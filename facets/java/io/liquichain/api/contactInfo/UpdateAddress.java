package io.liquichain.api.contactInfo;

import java.util.HashMap;
import java.util.Map;

import org.meveo.api.persistence.CrossStorageApi;
import org.meveo.model.customEntities.Address;
import org.meveo.model.customEntities.VerifiedPhoneNumber;
import org.meveo.model.customEntities.Wallet;
import org.meveo.model.storage.Repository;
import org.meveo.service.script.Script;
import org.meveo.admin.exception.BusinessException;
import org.meveo.service.storage.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateAddress extends Script {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateAddress.class);
    private final CrossStorageApi crossStorageApi = getCDIBean(CrossStorageApi.class);
    private final RepositoryService repositoryService = getCDIBean(RepositoryService.class);
    private final Repository defaultRepo = repositoryService.findDefaultRepository();

    private String uuid;
    private String name;
    private String streetAddress;
    private String city;
    private String state;
    private String countryCode;
    private String dialCode;
    private String postalCode;
    private Double longitude;
    private Double latitude;
    private String walletId;
    private String phoneNumber;
    private String notes;
    private Boolean isDefault;
    private final Map<String, Object> result = new HashMap<>();

    public Map<String, Object> getResult() {
        return result;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setWalletId(String walletId) {
        if (walletId == null) {
            this.walletId = walletId;
        } else {
            this.walletId = (walletId.startsWith("0x") ? walletId.substring(2) : walletId).toLowerCase();
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault == null ? false : isDefault;
    }

    @Override
    public void execute(Map<String, Object> parameters) throws BusinessException {
        super.execute(parameters);
        Address address = null;
        try {
            address = crossStorageApi.find(defaultRepo, uuid, Address.class);
        } catch (Exception e) {
            String errorMessage = "Failed to retrieve address with id " + uuid;
            LOG.error(errorMessage, e);
            result.put("status", "fail");
            result.put("result", errorMessage);
        }

        if (address == null) {
            String errorMessage = String.format("Address with id %s not found", uuid);
            LOG.error(errorMessage);
            result.put("status", "fail");
            result.put("result", errorMessage);
            return;
        }

        Wallet wallet;
        try {
            wallet = crossStorageApi.find(defaultRepo, walletId, Wallet.class);
        } catch (Exception e) {
            String errorMessage = String.format("Wallet with id %s not found", walletId);
            LOG.error(errorMessage, e);
            result.put("status", "fail");
            result.put("result", errorMessage);
            return;
        }

        VerifiedPhoneNumber verifiedPhoneNumber = null;
        if (phoneNumber != null) {
            try {
                verifiedPhoneNumber = crossStorageApi.find(defaultRepo, VerifiedPhoneNumber.class)
                                                     .by("walletId", walletId)
                                                     .by("phoneNumber", phoneNumber)
                                                     .getResult();
            } catch (Exception e) {
                // do nothing, create a new phone number instead
            }

            if (verifiedPhoneNumber == null) {
                verifiedPhoneNumber = new VerifiedPhoneNumber();
            }

            try {
                verifiedPhoneNumber.setPhoneNumber(phoneNumber);
                verifiedPhoneNumber.setWalletId(walletId);
                String uuid = crossStorageApi.createOrUpdate(defaultRepo, verifiedPhoneNumber);
                verifiedPhoneNumber.setUuid(uuid);
            } catch (Exception e) {
                String errorMessage = "Failed to save phone number: " + phoneNumber;
                LOG.error(errorMessage, e);
                result.put("status", "fail");
                result.put("result", errorMessage);
                return;
            }
        }

        address.setName(name);
        address.setStreetAddress(streetAddress);
        address.setCity(city);
        address.setState(state);
        address.setCountryCode(countryCode);
        address.setDialCode(dialCode);
        address.setPostalCode(postalCode);
        address.setLongitude(longitude);
        address.setLatitude(latitude);
        address.setWallet(wallet);
        address.setPhoneNumber(verifiedPhoneNumber);
        address.setNotes(notes);
        address.setIsDefault(isDefault);

        try {
            if (address.getIsDefault()) {
                Address defaultAddress = crossStorageApi.find(defaultRepo, Address.class)
                                                        .by("wallet", walletId)
                                                        .by("isDefault", true)
                                                        .getResult();
                if (defaultAddress != null && !address.getUuid().equals(defaultAddress.getUuid())) {
                    defaultAddress.setIsDefault(false);
                    crossStorageApi.createOrUpdate(defaultRepo, defaultAddress);
                }
            }
            String uuid = crossStorageApi.createOrUpdate(defaultRepo, address);
            address.setUuid(uuid);
        } catch (Exception e) {
            String errorMessage = "Failed to save address: " + address;
            LOG.error(errorMessage, e);
            result.put("status", "fail");
            result.put("result", errorMessage);
            return;
        }

        Map<String, Object> addressDetails = new HashMap<>();
        addressDetails.put("uuid", address.getUuid());
        addressDetails.put("name", address.getName());
        addressDetails.put("streetAddress", address.getStreetAddress());
        addressDetails.put("city", address.getCity());
        addressDetails.put("state", address.getState());
        addressDetails.put("countryCode", address.getCountryCode());
        addressDetails.put("dialCode", address.getDialCode());
        addressDetails.put("postalCode", address.getPostalCode());
        addressDetails.put("longitude", address.getLongitude());
        addressDetails.put("latitude", address.getLatitude());
        addressDetails.put("walletId", address.getWallet().getUuid());
        addressDetails.put("phoneNumber",
            address.getPhoneNumber() != null ? address.getPhoneNumber().getPhoneNumber() : null);
        addressDetails.put("notes", address.getNotes());
        addressDetails.put("isDefault", address.getIsDefault());

        result.put("status", "success");
        result.put("result", addressDetails);
    }

}
