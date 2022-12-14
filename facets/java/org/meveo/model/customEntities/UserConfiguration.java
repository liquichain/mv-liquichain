package org.meveo.model.customEntities;

import org.meveo.model.CustomEntity;
import java.util.List;
import org.meveo.model.persistence.DBStorageType;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserConfiguration implements CustomEntity {

    public UserConfiguration() {
    }

    public UserConfiguration(String uuid) {
        this.uuid = uuid;
    }

    private String uuid;

    @JsonIgnore()
    private DBStorageType storages;

    private Boolean isChatNotificationsEnabled;

    @Override()
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public DBStorageType getStorages() {
        return storages;
    }

    public void setStorages(DBStorageType storages) {
        this.storages = storages;
    }

    public Boolean getIsChatNotificationsEnabled() {
        return isChatNotificationsEnabled;
    }

    public void setIsChatNotificationsEnabled(Boolean isChatNotificationsEnabled) {
        this.isChatNotificationsEnabled = isChatNotificationsEnabled;
    }

    @Override()
    public String getCetCode() {
        return "UserConfiguration";
    }
}
