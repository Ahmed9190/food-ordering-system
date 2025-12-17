package com.food.ordering.system.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
  private final BigDecimal amount;

  public static final Money ZERO = new Money(BigDecimal.ZERO);

  public Money(BigDecimal amount) {
    this.amount = amount;
  }

  public boolean isGreaterThanZero() {
    return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
  }

  public boolean isGreaterThan(Money money) {
    return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
  }

  public Money add(Money money) {
    if (this.amount == null || money.getAmount() == null) {
      throw new IllegalArgumentException("Amount is null");
    }
    return new Money(this.setScale(this.amount.add(money.getAmount())));
  }

  public Money subtract(Money money) {
    if (this.amount == null || money.getAmount() == null) {
      throw new IllegalArgumentException("Amount is null");
    }
    return new Money(this.setScale(this.amount.subtract(money.getAmount())));
  }

  public Money multiply(int multiplier) {
    return new Money(this.setScale(this.amount.multiply(new BigDecimal(multiplier))));
  }

  public BigDecimal getAmount() {
    return amount;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((amount == null) ? 0 : amount.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Money other = (Money) obj;
    if (amount == null) {
      if (other.amount != null)
        return false;
    } else if (!amount.equals(other.amount))
      return false;
    return true;
  }

  private BigDecimal setScale(BigDecimal input) {
    return input.setScale(2, RoundingMode.HALF_EVEN);
  }
}
