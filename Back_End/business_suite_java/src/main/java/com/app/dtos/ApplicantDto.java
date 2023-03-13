package com.app.dtos;

import java.time.LocalDate;

import com.app.entities.custom_enum.Gender;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApplicantDto {
	
	private String firstName;
	private String middleName;
	private String lastName;
	private Long prn;
	private String emailId;
	private String priContact;
	private String secContact;
	private int expYears;
	private String address;
	private LocalDate dob;
	private Gender gender;
	private String highestEducationProfile;
	private double score12th;
	private LocalDate passingYear12th;
	private LocalDate passingYear10th;
	private double score10th;
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
	public Long getPrn() {
		return prn;
	}
	public void setPrn(Long prn) {
		this.prn = prn;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPriContact() {
		return priContact;
	}
	public void setPriContact(String priContact) {
		this.priContact = priContact;
	}
	public String getSecContact() {
		return secContact;
	}
	public void setSecContact(String secContact) {
		this.secContact = secContact;
	}
	public int getExpYears() {
		return expYears;
	}
	public void setExpYears(int expYears) {
		this.expYears = expYears;
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
		this.gender =Gender.valueOf(gender.toUpperCase()) ;
	}
	public String getHighestEducationProfile() {
		return highestEducationProfile;
	}
	public void setHighestEducationProfile(String highestEducationProfile) {
		this.highestEducationProfile = highestEducationProfile;
	}
	public double getScore12th() {
		return score12th;
	}
	public void setScore12th(double score12th) {
		this.score12th = score12th;
	}
	public LocalDate getPassingYear12th() {
		return passingYear12th;
	}
	public void setPassingYear12th(LocalDate passingYear12th) {
		this.passingYear12th = passingYear12th;
	}
	public LocalDate getPassingYear10th() {
		return passingYear10th;
	}
	public void setPassingYear10th(LocalDate passingYear10th) {
		this.passingYear10th = passingYear10th;
	}
	public double getScore10th() {
		return score10th;
	}
	public void setScore10th(double score10th) {
		this.score10th = score10th;
	}
	public ApplicantDto(String firstName, String middleName, String lastName, Long prn, String emailId,
			String priContact, String secContact, int expYears, String address, LocalDate dob, String gender,
			String highestEducationProfile, double score12th, LocalDate passingYear12th, LocalDate passingYear10th,
			double score10th) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.prn = prn;
		this.emailId = emailId;
		this.priContact = priContact;
		this.secContact = secContact;
		this.expYears = expYears;
		this.address = address;
		this.dob = dob;
		this.gender =Gender.valueOf(gender.toUpperCase());
		this.highestEducationProfile = highestEducationProfile;
		this.score12th = score12th;
		this.passingYear12th = passingYear12th;
		this.passingYear10th = passingYear10th;
		this.score10th = score10th;
	}
	
}
