package com.num26.transactions.domain;

import javax.validation.constraints.NotNull;

public class Transaction {
    private Long transaction_id;
    @NotNull
    private Double amount;
    @NotNull
    private String type;
    private Long parent_id;


    public Long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public Transaction() {
    }

    public Transaction(Double amount, String type, Long parent_id) {
        this.amount = amount;
        this.type = type;
        this.parent_id = parent_id;
    }

    public Transaction(Long transaction_id, Double amount, String type, Long parent_id) {
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.type = type;
        this.parent_id = parent_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (transaction_id != null ? !transaction_id.equals(that.transaction_id) : that.transaction_id != null)
            return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return parent_id != null ? parent_id.equals(that.parent_id) : that.parent_id == null;

    }

    @Override
    public int hashCode() {
        int result = transaction_id != null ? transaction_id.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (parent_id != null ? parent_id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id=" + transaction_id +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", parent_id=" + parent_id +
                '}';
    }
}
