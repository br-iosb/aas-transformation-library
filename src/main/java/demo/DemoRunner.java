package demo;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

	public static void main(String[] args) throws Exception {

		Path demoSpace = Paths.get("src/main/resources/demo").toAbsolutePath();

		Path execDir = demoSpace.resolve(_01_FULL_SUPPORT);

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
			}
		}
		
		//consistentFormat();
//		new JsonMapper().readTree(Files.newInputStream(pathOut, StandardOpenOption.))

	}

}
