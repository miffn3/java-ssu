package bank.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Account {
    private UUID id;
    private String clientId;
    private BigDecimal amount;
    private String accCode;

    public Account() {
    }

    public Account(UUID id, String clientId, BigDecimal amount, String accCode) {
        this.id = id;
        this.clientId = clientId;
        this.amount = BigDecimal.valueOf(0);
        this.accCode = accCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return id.equals(account.id) &&
                clientId.equals(account.clientId) &&
                amount.equals(account.amount) &&
                accCode.equals(account.accCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, amount, accCode);
    }
}
