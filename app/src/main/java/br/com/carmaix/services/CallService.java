package br.com.carmaix.services;

public class CallService {

    public static String getVersionReleaseServerLogged() {

        /*
        ApplicationFluig applicationFluig = (ApplicationFluig) Utils.getContextApplication();

        if (applicationFluig.getServerLoggued() == null) {
            return ConstantsLibrary.DEFAULT_VERSION_SEVER;
        }

        return applicationFluig.getServerLoggued().getServer_version();

        */

        return "1.0";

    }

    public static VersionRelease getVersionRelease() {
        return getVersionRelease(getVersionReleaseServerLogged());
    }

    private static VersionRelease getVersionRelease(String versionRequired) {

        if (versionRequired == null || versionRequired.equals("")) {
            return getVersionRelease();
        } else {

            if (versionRequired.startsWith("1.0")) {
                return new ServiceDefault();
            }

            return new ServiceDefault();

        }

    }

    public static boolean isSupportWidgetIdentity() throws Exception {
        return getVersionRelease().isSupportWidgetIdentity();
    }


}