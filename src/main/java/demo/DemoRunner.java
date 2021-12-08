package demo;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.sap.dsc.aas.lib.mapping.MappingSpecificationParser;
import com.sap.dsc.aas.lib.mapping.model.MappingSpecification;
import com.sap.dsc.aas.lib.transform.GenericDocumentTransformer;

import io.adminshell.aas.v3.dataformat.json.JsonSerializer;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;

public class DemoRunner {

	private static final String IN = "input.xml";
	private static final String CONFIG = "config.json";
	private static final String OUT = "output.json";

	private static final String _01_FULL_SUPPORT = "01_fullSupport";
	private static final String _02_EXPRESSIONS = "02_bindWithExpression";
	private static final String _03_VARS = "03_vars";
	private static final String _04_FOREACH = "04_foreach";
	private static final String _05_DEFINITIONS = "05_definitions";
	private static final String _06_VARSCOPE = "06_variableScope";

	public static void main(String[] args) throws Exception {
		String scenarioName = _06_VARSCOPE;
		
		Path demoSpace = Paths.get("src/main/resources/demo").toAbsolutePath();
		Path execDir = demoSpace.resolve(scenarioName);

		Path pathIn = execDir.resolve(IN);
		Path pathConfig = execDir.resolve(CONFIG);
		Path pathOut = execDir.resolve(OUT);

		try (InputStream inputStream = Files.newInputStream(pathIn, StandardOpenOption.READ)) {
			MappingSpecification mappingSpecification = new MappingSpecificationParser()
					.loadMappingSpecification(pathConfig.toAbsolutePath().toString());
			AssetAdministrationShellEnvironment transformationResults = new GenericDocumentTransformer()
					.execute(inputStream, mappingSpecification);

			try (OutputStream outputStream = Files.newOutputStream(pathOut, StandardOpenOption.CREATE,
					StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
				new JsonSerializer().write(outputStream, transformationResults);
				System.out.println(scenarioName);
				new JsonSerializer().write(System.out, transformationResults);
			}
		}

		withDefaultFormat(pathConfig);
		withDefaultFormat(pathOut);
	}

	private static void withDefaultFormat(Path pathConfig)
			throws IOException, JsonGenerationException, JsonMappingException {
		JsonNode readTree = new JsonMapper().readTree(Files.newInputStream(pathConfig, StandardOpenOption.READ));
		new JsonMapper().writerWithDefaultPrettyPrinter().writeValue(pathConfig.toFile(), readTree);
	}

}
