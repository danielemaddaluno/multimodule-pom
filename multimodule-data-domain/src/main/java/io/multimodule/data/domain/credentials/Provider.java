package io.multimodule.data.domain.credentials;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Locale;

import org.apache.commons.lang3.SerializationUtils;
import org.joda.money.CurrencyUnit;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Provider implements Serializable {

	private static final long serialVersionUID = -7818639983354789644L;
	private BigInteger id;
	/**
	 * Company or individual name
	 */
	@JsonIgnore
	private String name;
	@JsonIgnore
	private Locale locale;
	@JsonIgnore
	private CurrencyUnit currencyUnit;
	@JsonIgnore
	private String vatNumber;
	@JsonIgnore
	private String phone;
	@JsonIgnore
	private String website;
	@JsonIgnore
	private String description;
	@JsonIgnore
	private String descriptionLocations;
	@JsonIgnore
	private String stripeUserId;
	@JsonIgnore
	private User subscriber;

	@JsonIgnore
	private int locationsCount;
	@JsonIgnore
	private Boolean active;
	@JsonIgnore
	private DateTime date;

	public Provider clone() {
		return SerializationUtils.clone(this);
	}

	public static Provider fromId(BigInteger providerId) {
		Provider provider = new Provider();
		provider.setId(providerId);
		return provider;
	}

	public static boolean checkNull(Provider provider) {
		return provider == null || provider.getName() == null || provider.getLocale() == null
				|| provider.getCurrencyUnit() == null || provider.getPhone() == null
				|| provider.getDescription() == null || provider.getDescriptionLocations() == null;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public CurrencyUnit getCurrencyUnit() {
		return currencyUnit;
	}

	public void setCurrencyUnit(CurrencyUnit currencyUnit) {
		this.currencyUnit = currencyUnit;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStripeUserId() {
		return stripeUserId;
	}

	public void setStripeUserId(String stripeUserId) {
		this.stripeUserId = stripeUserId;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionLocations() {
		return descriptionLocations;
	}

	public void setDescriptionLocations(String descriptionLocations) {
		this.descriptionLocations = descriptionLocations;
	}

	public int getLocationsCount() {
		return locationsCount;
	}

	public void setLocationsCount(int locationsCount) {
		this.locationsCount = locationsCount;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public User getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(User subscriber) {
		this.subscriber = subscriber;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provider other = (Provider) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
