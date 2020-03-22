package bank.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Operation {
    private UUID id;
    private String date;
    private String currency;
    private String accountFrom;
    private String accountTo;
    private BigDecimal amount;
    private BigDecimal moneyBefore;
    private BigDecimal moneyAfter;

    public Operation() {
    }

    public Operation(UUID id, String date, String currency, String accountFrom, String accountTo, BigDecimal amount,
                     BigDecimal moneyBefore, BigDecimal moneyAfter) {
        this.id = id;
        this.date = date;
        this.currency = currency;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.moneyBefore = moneyBefore;
        this.moneyAfter = moneyAfter;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getMoneyBefore() {
        return moneyBefore;
    }

    public void setMoneyBefore(BigDecimal moneyBefore) {
        this.moneyBefore = moneyBefore;
    }

    public BigDecimal getMoneyAfter() {
        return moneyAfter;
    }

    public void setMoneyAfter(BigDecimal moneyAfter) {
        this.moneyAfter = moneyAfter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operation)) return false;
        Operation operation = (Operation) o;
        return id.equals(operation.id) &&
                date.equals(operation.date) &&
                currency.equals(operation.currency) &&
                accountFrom.equals(operation.accountFrom) &&
                accountTo.equals(operation.accountTo) &&
                amount.equals(operation.amount) &&
                moneyBefore.equals(operation.moneyBefore) &&
                moneyAfter.equals(operation.moneyAfter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, currency, accountFrom, accountTo, amount, moneyBefore, moneyAfter);
    }
}
