package org.meveo.model.customEntities;

import org.meveo.model.CustomEntity;
import java.io.Serializable;
import java.util.List;
import org.meveo.model.persistence.DBStorageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Transaction implements CustomEntity, Serializable {

    public Transaction() {
    }

    public Transaction(String uuid) {
        this.uuid = uuid;
    }

    private String uuid;

    @JsonIgnore()
    private DBStorageType storages;

    private String blockHash;

    private String data;

    private String nodeSignature;

    private String initiator;

    private String fromHexHash;

    private Long transactionIndex;

    private String rawData;

    private Instant creationDate;

    private String type;

    private String nonce;

    private String gasLimit;

    private String r;

    private String toHexHash;

    private String s;

    private String assetId;

    private String v;

    private String blockNumber;

    @JsonProperty(required = true)
    private String hexHash;

    @JsonProperty(required = true)
    private String signedHash;

    private String value;

    private String gasPrice;

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

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNodeSignature() {
        return nodeSignature;
    }

    public void setNodeSignature(String nodeSignature) {
        this.nodeSignature = nodeSignature;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getFromHexHash() {
        return fromHexHash;
    }

    public void setFromHexHash(String fromHexHash) {
        this.fromHexHash = fromHexHash;
    }

    public Long getTransactionIndex() {
        return transactionIndex;
    }

    public void setTransactionIndex(Long transactionIndex) {
        this.transactionIndex = transactionIndex;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(String gasLimit) {
        this.gasLimit = gasLimit;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getToHexHash() {
        return toHexHash;
    }

    public void setToHexHash(String toHexHash) {
        this.toHexHash = toHexHash;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getHexHash() {
        return hexHash;
    }

    public void setHexHash(String hexHash) {
        this.hexHash = hexHash;
    }

    public String getSignedHash() {
        return signedHash;
    }

    public void setSignedHash(String signedHash) {
        this.signedHash = signedHash;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    @Override()
    public String getCetCode() {
        return "Transaction";
    }
}
