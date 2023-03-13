package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import com.app.entities.custom_enum.Gender;
import com.app.entities.custom_enum.GovernmentId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass
public class Person {
	@Column(length = 50, name = "first_name")
	@NotBlank(message = "First Name is required!!!!")
	private String firstName;
	@Column(length = 50, name = "middle_name")
	@NotBlank(message = "Middle Name is required!!!!")
	private String middleName;
	@Column(length = 50, name = "last_name")
	@NotBlank(message = "Last Name is required!!!!")
	private String lastName;
	@Column(length = 50, name = "address")
	@NotBlank(message = "Address is required!!!!")
	private String address;
	@Column(name = "dob")
	private LocalDate dob;
	@Column(name = "gender", length = 50)
	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	// below add data members for Resume PDF, Image, Government Id Image
	@Column(name = "profile_image_path")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String profileImagePath;
	@Enumerated(EnumType.STRING)
	@Column(name = "government_Id_type")
	private GovernmentId govIdType;
	@Column(name = "government_Id_number")
	private Long governmentIdNumber;
	@Column(name = "government_id_image_path")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String governmentIdImagePath;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = Gender.valueOf(gender.toUpperCase());
	}

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}

	public GovernmentId getGovIdType() {
		return govIdType;
	}

	public void setGovIdType(String govIdType) {
		this.govIdType = GovernmentId.valueOf(govIdType.toUpperCase());
	}

	public Long getGovernmentIdNumber() {
		return governmentIdNumber;
	}

	public void setGovernmentIdNumber(Long governmentIdNumber) {
		this.governmentIdNumber = governmentIdNumber;
	}

	public String getGovernmentIdImagePath() {
		return governmentIdImagePath;
	}

	public void setGovernmentIdImagePath(String governmentIdImagePath) {
		this.governmentIdImagePath = governmentIdImagePath;
	}

	public Person(@NotBlank(message = "First Name is required!!!!") String firstName,
			@NotBlank(message = "Middle Name is required!!!!") String middleName,
			@NotBlank(message = "Last Name is required!!!!") String lastName,
			@NotBlank(message = "Address is required!!!!") String address, LocalDate dob, Gender gender,
			Long governmentIdNumber, String govIdType) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.address = address;
		this.dob = dob;
		this.gender = gender;
		this.governmentIdNumber =governmentIdNumber;
		this.govIdType=GovernmentId.valueOf(govIdType);
	}

}
