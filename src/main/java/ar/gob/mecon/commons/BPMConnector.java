package ar.gob.mecon.commons;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.kie.api.command.Command;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.remote.client.api.RemoteRuntimeEngineFactory;
import org.kie.remote.jaxb.gen.GetProcessIdsCommand;

public class BPMConnector {
	private static final String BASE_URL = "http://{HOST}:{PORT}/business-central/";
	private KieSession remoteSession;

	public BPMConnector(URL baseURL, String deploymentId, String user,
			String password) {
		RuntimeEngine engine = RemoteRuntimeEngineFactory.newRestBuilder()
				.addUrl(baseURL).addTimeout(5).addDeploymentId(deploymentId)
				.addUserName(user).addPassword(password).build();
		remoteSession = engine.getKieSession();
	}

	@SuppressWarnings("unchecked")
	public List<String> getProcessIds() throws MalformedURLException {
		Command<List<String>> command = new GetProcessIdsCommand();
		return remoteSession.execute(command);
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 5) {
			throw new Exception(
					"String 'host', String 'port', String 'deploymentId', String 'user', String 'password' son requeridos");
		}
		String baseURL = BASE_URL.replace("{HOST}", args[0]).replace("{PORT}",
				args[1]);
		BPMConnector connector = new BPMConnector(new URL(baseURL), args[2],
				args[3], args[4]);
		List<String> processes = connector.getProcessIds();
		for (String process : processes) {
			System.out.println("-----Proceso: " + process);
		}
	}
}
