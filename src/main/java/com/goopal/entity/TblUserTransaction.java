package com.goopal.entity;

import java.util.Date;

public class TblUserTransaction {
    private Long id;

    private String trxFromAddress;

    private String transferFromAddress;

    private String transferToAddress;

    private Date trxTime;

    private String trxId;

    private String extTrxId;

    private String blockId;

    private Integer blockNum;

    private Integer blockTrxNum;

    private Byte trxType;

    private Byte trxState;

    private Double transferAmount;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrxFromAddress() {
        return trxFromAddress;
    }

    public void setTrxFromAddress(String trxFromAddress) {
        this.trxFromAddress = trxFromAddress == null ? null : trxFromAddress.trim();
    }

    public String getTransferFromAddress() {
        return transferFromAddress;
    }

    public void setTransferFromAddress(String transferFromAddress) {
        this.transferFromAddress = transferFromAddress == null ? null : transferFromAddress.trim();
    }

    public String getTransferToAddress() {
        return transferToAddress;
    }

    public void setTransferToAddress(String transferToAddress) {
        this.transferToAddress = transferToAddress == null ? null : transferToAddress.trim();
    }

    public Date getTrxTime() {
        return trxTime;
    }

    public void setTrxTime(Date trxTime) {
        this.trxTime = trxTime;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId == null ? null : trxId.trim();
    }

    public String getExtTrxId() {
        return extTrxId;
    }

    public void setExtTrxId(String extTrxId) {
        this.extTrxId = extTrxId == null ? null : extTrxId.trim();
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId == null ? null : blockId.trim();
    }

    public Integer getBlockNum() {
        return blockNum;
    }

    public void setBlockNum(Integer blockNum) {
        this.blockNum = blockNum;
    }

    public Integer getBlockTrxNum() {
        return blockTrxNum;
    }

    public void setBlockTrxNum(Integer blockTrxNum) {
        this.blockTrxNum = blockTrxNum;
    }

    public Byte getTrxType() {
        return trxType;
    }

    public void setTrxType(Byte trxType) {
        this.trxType = trxType;
    }

    public Byte getTrxState() {
        return trxState;
    }

    public void setTrxState(Byte trxState) {
        this.trxState = trxState;
    }

    public Double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}