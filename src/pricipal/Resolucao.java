package pricipal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Permission;

public class Resolucao {

	public static void main(String[] args) throws Exception {
		DoNotTerminate.forbidExit();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int num = Integer.parseInt(br.readLine().trim());
			Object o;

			
			Inner x = new Inner();
			o = x.new Private();
			Inner.Private y = (Inner.Private) o;
			System.out.println(num + " is " + y.powerof2(num));

			System.out.println("An instance of class: " + o.getClass().getCanonicalName() + " has been created");

		} 

		catch (DoNotTerminate.ExitTrappedException e) {
			System.out.println("Unsuccessful Termination!!");
		}
	}

	static class Inner {
		private class Private {
			private String powerof2(int num) {
				return ((num & num - 1) == 0) ? "power of 2" : "not a power of 2";
			}
		}
	}

}

class DoNotTerminate { 

	public static class ExitTrappedException extends SecurityException {

		private static final long serialVersionUID = 1L;
	}

	public static void forbidExit() {
		final SecurityManager securityManager = new SecurityManager() {
			@Override
			public void checkPermission(Permission permission) {
				if (permission.getName().contains("exitVM")) {
					throw new ExitTrappedException();
				}
			}
		};
		System.setSecurityManager(securityManager);
	}
}
