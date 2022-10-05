package io.liquichain.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import org.meveo.model.customEntities.UserConfiguration;
import org.meveo.model.customEntities.Wallet;

import org.meveo.api.persistence.CrossStorageApi;
import org.meveo.model.storage.Repository;
import org.meveo.service.storage.RepositoryService;
import org.meveo.commons.utils.StringUtils;

import org.meveo.service.script.Script;
import org.meveo.admin.exception.BusinessException;

import com.google.gson.Gson;

public class GetUserConfigsByWalletId extends Script {

    private String walletId;
    private String result;

    private static final Logger log = LoggerFactory.getLogger(GetUserConfigsByWalletId.class);
    private final CrossStorageApi crossStorageApi = getCDIBean(CrossStorageApi.class);
    private final RepositoryService repositoryService = getCDIBean(RepositoryService.class);
    private final Repository defaultRepo = repositoryService.findDefaultRepository();

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    private String returnError(String errorCode, String message) {
        String res = "{\n";
        res += "  \"error\": { \"code\" : \"" + errorCode + "\" , \"message\" : \"" + message + "\"}\n";
        res += "}";
        return res;
    }

    public String getResult() {
        return this.result;
    }


    @Override
    public void execute(Map<String, Object> parameters) throws BusinessException {
        try {
            log.info("filter user settings by walletId == {}", walletId);
            if (StringUtils.isBlank(walletId)) {
                result = returnError("USER_NOT_FOUND", "invalid walletId");
                return;
            }
            walletId = (walletId.startsWith("0x") ? walletId.substring(2) : walletId).toLowerCase();
            Wallet user = crossStorageApi.find(defaultRepo, Wallet.class).by("uuid", walletId).getResult();
            if (user == null) {
                result = returnError("USER_NOT_FOUND", "user not found against provided walletId.");
                return;
            }

            UserUtils userUtils = new UserUtils(crossStorageApi, defaultRepo, user);
            UserConfiguration configs = userUtils.getUserConfigurationsByWalletId(walletId);
            log.info("isEmailNotificationsEnabled == {}", userUtils.isUserEmailNotificationsAllowed(walletId));

            result = new Gson().toJson(configs);
        } catch (Exception e) {
            log.error("User's configurations not found against provided provided data.", e);
            result = returnError("USER_CONFIG_NOT_FOUND", "User's configurations not found against provided data.");
        }
    }

}
