package br.com.carmaix.database;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import br.com.carmaix.application.ApplicationCarmaix;
import br.com.carmaix.services.TokenConvertedReturn;
import br.com.carmaix.services.TokenReturn;
import br.com.carmaix.services.UserReturn;

public class DataBaseUtils {

    /*
    public static boolean isNewServer(ApplicationAction application, String server) throws SQLException {

        RuntimeExceptionDao<ServerInformation, Integer> serversDao = application.getServerInformationHelper().getSimpleServerInformationDao();

        QueryBuilder<ServerInformation, Integer> queryBuilder = serversDao.queryBuilder();
        queryBuilder.where().eq(ServerInformation.SERVER_ENABLE, true);
        PreparedQuery<ServerInformation> preparedQuery = queryBuilder.prepare();
        List<ServerInformation> listServers = serversDao.query(preparedQuery);

        for (ServerInformation serverInformation : listServers) {
            if (Functions.getBaseUrlServer(serverInformation.getUrl()).toLowerCase().equals(Functions.getBaseUrlServer(server).toLowerCase())) {
                return false;
            }
        }

        return true;
    }

    public static Collection<ServerInformation> getListTenants(Context context, MethodType methodType)
            throws Exception {
        return ((ApplicationAction) context.getApplicationContext()).getListTenants(context, methodType);
    }

    public static int getSpecificTenantByServer(ApplicationAction application, MethodType methodType, String server)
            throws Exception {

        RuntimeExceptionDao<ServerInformation, Integer> serversDao = application.getServerInformationHelper().getSimpleServerInformationDao();

        QueryBuilder<ServerInformation, Integer> queryBuilder = serversDao.queryBuilder();

        queryBuilder.where().like(ServerInformation.SERVER_URL_BASE, Functions.getBaseUrlServer(server));

        PreparedQuery<ServerInformation> preparedQuery = queryBuilder.prepare();

        ServerInformation serverInformation = serversDao.queryForFirst(preparedQuery);

        if (serverInformation != null) {
            return serverInformation.getId();
        }

        return 0;

    }

    public static int getSpecificTenantByServerHost(ApplicationAction application, MethodType methodType, String host, String server)
            throws Exception {

        ArrayList<ServerInformation> serverInformations = (ArrayList<ServerInformation>) getAllServers(application);
        ArrayList<ServerInformation> serverInformationsAux = new ArrayList<ServerInformation>();

        if (serverInformations != null && serverInformations.size() > 0){

            for (ServerInformation serverInformation : serverInformations){

                if (compareServerContains(serverInformation.getUrl(), host)){
                    serverInformationsAux.add(serverInformation);
                }

            }

        }

        if (serverInformationsAux.size() == 1){
            return serverInformationsAux.get(0).getId();
        } else {

            RuntimeExceptionDao<ServerInformation, Integer> serversDao = application.getServerInformationHelper().getSimpleServerInformationDao();

            QueryBuilder<ServerInformation, Integer> queryBuilder = serversDao.queryBuilder();

            queryBuilder.where().like(ServerInformation.SERVER_URL_BASE, Functions.getBaseUrlServer(server));

            PreparedQuery<ServerInformation> preparedQuery = queryBuilder.prepare();

            ServerInformation serverInformation = serversDao.queryForFirst(preparedQuery);

            if (serverInformation != null) {
                return serverInformation.getId();
            }

        }

        return 0;

    }


    public static Boolean isServerEnable(ApplicationAction application, int serverId) throws Exception {

        RuntimeExceptionDao<ServerInformation, Integer> simpleDao = application.getServerInformationHelper().getSimpleServerInformationDao();

        QueryBuilder<ServerInformation, Integer> queryBuilder = simpleDao.queryBuilder();
        queryBuilder.where().eq(ServerInformation.SERVER_ID, serverId);
        PreparedQuery<ServerInformation> preparedQuery = queryBuilder.prepare();

        ServerInformation serverInformation = simpleDao.queryForFirst(preparedQuery);

        if (serverInformation != null && serverInformation.isEnable()) {
            return true;
        }

        return false;

    }

    public static Boolean userIsLogged(ApplicationAction application) throws Exception {

        RuntimeExceptionDao<ServerInformation, Integer> simpleDao = application.getServerInformationHelper().getSimpleServerInformationDao();

        QueryBuilder<ServerInformation, Integer> queryBuilder = simpleDao.queryBuilder();

        queryBuilder.where().eq(ServerInformation.SERVER_LOGGED_ACTUALLY, true);

        PreparedQuery<ServerInformation> preparedQuery = queryBuilder.prepare();

        List<ServerInformation> accountList = simpleDao.query(preparedQuery);

        if (accountList != null && accountList.size() > 0) {
            return true;
        }

        return false;

    }

    public static Boolean existsServersInBase(ApplicationAction application) throws Exception {

        RuntimeExceptionDao<ServerInformation, Integer> serversDao = application.getServerInformationHelper().getSimpleServerInformationDao();
        List<ServerInformation> list = serversDao.queryForAll();

        if (list != null && list.size() > 0) {
            return true;
        }

        return false;

    }

    */

    public static void createAccount(ApplicationCarmaix application, String userEmail, TokenReturn tokenReturn) throws Exception {

        LoginTable loginTable = new LoginTable();

        loginTable.setUserEmail(userEmail);

        loginTable.setToken(tokenReturn.getToken());

        loginTable.setCompanyName("CompanyName");

        loginTable.setCompanyLogo("CompanyLogo");

        RuntimeExceptionDao<LoginTable, Integer> simpleDao = application.getHelper().getSimpleServerInformationDao();

        simpleDao.create(loginTable);

    }

    /*

    public static void updateTenantServer(ApplicationAction application, int serverId, boolean changeServer,
                                          String userID, String userAlias, String tenantId, String serverVersion) throws Exception {

        RuntimeExceptionDao<ServerInformation, Integer> simpleDao = application.getServerInformationHelper().getSimpleServerInformationDao();

        if (changeServer) {
            UpdateBuilder<ServerInformation, Integer> updateBuilder = simpleDao.updateBuilder();
            updateBuilder.updateColumnValue(ServerInformation.SERVER_LOGGED_ACTUALLY, false);
            updateBuilder.update();
        }

        QueryBuilder<ServerInformation, Integer> queryBuilder = simpleDao.queryBuilder();
        queryBuilder.where().eq(ServerInformation.SERVER_ID, serverId);
        PreparedQuery<ServerInformation> preparedQuery = queryBuilder.prepare();

        ServerInformation serverInformation = simpleDao.queryForFirst(preparedQuery);

        if (serverInformation != null) {

            serverInformation.setLogged_actually(true);
            serverInformation.setServer_user_id(userID);
            serverInformation.setUser_login(userAlias);
            serverInformation.setTenant_identifier_actually(tenantId);
            serverInformation.setServer_version(serverVersion);

            if (!serverInformation.getServer_version().equals(serverVersion)){
                serverInformation.setLast_update_version(new Date().getTime());
            }

            simpleDao.update(serverInformation);

        }

    }
    
    public static void updateTenantServerPushStatus(ApplicationAction application, ServerInformation serverInformation) throws Exception {

        RuntimeExceptionDao<ServerInformation, Integer> simpleDao = application.getServerInformationHelper().getSimpleServerInformationDao();

        simpleDao.update(serverInformation);

    }

    */

    public static LoginTable getLoginTable(ApplicationCarmaix application) throws Exception {

        RuntimeExceptionDao<LoginTable, Integer> simpleDao = application.getHelper().getSimpleServerInformationDao();

        QueryBuilder<LoginTable, Integer> queryBuilder = simpleDao.queryBuilder();

        PreparedQuery<LoginTable> preparedQuery = queryBuilder.prepare();

        return simpleDao.queryForFirst(preparedQuery);

    }

    /*

    public static void deleteServer(ApplicationAction application, String serverId) throws Exception {

        Integer serverIdentification = Integer.parseInt(serverId);
        RuntimeExceptionDao<ServerInformation, Integer> serversDao = application.getServerInformationHelper().getSimpleServerInformationDao();
        serversDao.deleteById(serverIdentification);

    }

    public static void deleteAllServers(ApplicationAction application) throws Exception {

        RuntimeExceptionDao<ServerInformation, Integer> serversDao = application.getServerInformationHelper().getSimpleServerInformationDao();
        List<ServerInformation> list = serversDao.queryForAll();
        serversDao.delete(list);

    }

    public static Collection<ServerInformation> getAllServers(ApplicationAction application) throws Exception {
        RuntimeExceptionDao<ServerInformation, Integer> serversDao = application.getServerInformationHelper().getSimpleServerInformationDao();
        return serversDao.queryForAll();
    }

    public static ServerInformation getServerById(ApplicationAction application, String id) throws Exception {
        RuntimeExceptionDao<ServerInformation, Integer> serversDao = application.getServerInformationHelper().getSimpleServerInformationDao();
        return serversDao.queryForId(Integer.parseInt(id));
    }

    public static void updateServer(ApplicationAction application, ServerInformation serverInformation) throws Exception {
        RuntimeExceptionDao<ServerInformation, Integer> serversDao = application.getServerInformationHelper().getSimpleServerInformationDao();
        serversDao.update(serverInformation);
    }

    public static void changeTokens(Context context, String token, String tokenSecret) throws Exception {

        ApplicationAction application = (ApplicationAction) context.getApplicationContext();

        if (null != token || null != tokenSecret) {

            String androidId = Functions.getIdentifyAndroid(context);

            token = Functions.decodeKeyFromBase64(token, androidId);
            tokenSecret = Functions.decodeKeyFromBase64(tokenSecret, androidId);

            application.setTokenaccess(token);
            application.setTokenaccessSecret(tokenSecret);

        }

    }

    public static boolean isDatabaseIrregularServers(ApplicationAction application) throws Exception {

        List<ServerInformation> list = (List<ServerInformation>) getAllServers(application);

        if (list != null && list.size() > 0) {

            ServerInformation serverInformation = getLoggedServer(application);

            if (serverInformation == null) {
                return true;
            }

        }

        return false;

    }

    public static void changeTenantServerNew(Context context, ServerInformation serverInformation) throws Exception {
        changeTenantServerNew(context, serverInformation.getId(), true, false, false);
    }

    public static boolean changeTenantServerNew(Context context, int serverId, boolean verifyOnlyChange)
            throws Exception {
        return changeTenantServerNew(context, serverId, false, false, verifyOnlyChange);
    }

    public static boolean changeTenantServerNew(Context context, int serverId, boolean ignoreServices,
                                                boolean throwException, boolean verifyOnlyChange) throws Exception {
        ApplicationAction application = (ApplicationAction) context.getApplicationContext();
        return application.changeTenantServerNew(context, serverId, ignoreServices, throwException, verifyOnlyChange);
    }

    public static boolean compareServerContains(String link, String server){
        return link.toLowerCase().contains(server.toLowerCase());
    }

    */

    public static void includeUserData(ApplicationCarmaix application, UserReturn user, TokenConvertedReturn tokenConvertedReturn, TokenReturn token, String userEmail) throws Exception {

        LoginTable loginTable = application.getLoginTable();
        loginTable.setUserEmail(userEmail);
        loginTable.setToken(token.getToken());
        loginTable.setUserId(user.getId());
        loginTable.setUserName(user.getNome());
        loginTable.setCompanyName(user.getNome_empresa());
        loginTable.setCompanyLogo(user.getLogo_empresa());
        loginTable.setUserId(user.getId());
        loginTable.setUserRevalida(tokenConvertedReturn.getUserRevalida());

        RuntimeExceptionDao<LoginTable, Integer> simpleDao = application.getHelper().getSimpleServerInformationDao();
        simpleDao.update(loginTable);

    }
}
