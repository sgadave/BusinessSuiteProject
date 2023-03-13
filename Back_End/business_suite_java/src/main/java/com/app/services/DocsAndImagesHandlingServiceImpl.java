package com.app.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dtos.ApiResponse;
import com.app.dtos.GovIdDto;
import com.app.entities.Applicant;
import com.app.entities.Employee;
import com.app.repositories.ApplicantRepository;
import com.app.repositories.EmployeeRepository;

@Service
@Transactional
public class DocsAndImagesHandlingServiceImpl implements DocsAndImagesHandlingService {

	@Value("${applicant.profile.upload.folder}")
	private String applicantProfileFolder;

	@Value("${employee.profile.upload.folder}")
	private String employeeProfileFolder;

	@Value("${applicant.government.id.upload.folder}")
	private String applicantGovernmentIdFolder;

	@Value("${employee.government.id.upload.folder}")
	private String employeeGovernmentIdFolder;

	@Value("${applicant.resume.upload.folder}")
	private String applicantResumeFolder;

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private ApplicantRepository appRepo;

	@PostConstruct
	public void myInit() {
		System.out.println("in myInit " + applicantProfileFolder);
		System.out.println("in myInit " + employeeProfileFolder);
		System.out.println("in myInit " + applicantGovernmentIdFolder);
		System.out.println("in myInit " + employeeGovernmentIdFolder);
		// chk of folder exists --o.w create one!
		File applicantProfilePath = new File(applicantProfileFolder);
		File applicantGovernmentPath = new File(applicantGovernmentIdFolder);
		File employeeProfilePath = new File(employeeProfileFolder);
		File employeeGovernmentIdPath = new File(employeeGovernmentIdFolder);
		File applicantResumeFolderPath = new File(applicantResumeFolder);
		
		if (!applicantProfilePath.exists()) {
			applicantProfilePath.mkdirs();
		} else if (!applicantGovernmentPath.exists()) {
			applicantGovernmentPath.mkdirs();
		} else if (!employeeProfilePath.exists()) {
			employeeProfilePath.mkdirs();
		} else if (!employeeGovernmentIdPath.exists()) {
			employeeGovernmentIdPath.mkdirs();
		} else if (!applicantResumeFolderPath.exists()) {
			applicantResumeFolderPath.mkdirs();
		} else {
			System.out.println("folders alrdy exists....");
		}

	}

	@Override
	public String uploadEmployeeProfileImage(String userName, MultipartFile imageFile) throws IOException {
		// TODO Auto-generated method stub
		Employee emp = empRepo.findByEmployeeCredentialsUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("InValid Employee Id !!!!! Employee Does Not Exists"));
		String targetPath = employeeProfileFolder + File.separator + "pi" + emp.getEmployeeId() + "."
				+ imageFile.getOriginalFilename().split("\\.")[1];
		System.out.println("Employee Profile Image Path : " + targetPath);
		Files.copy(imageFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
		emp.setProfileImagePath(targetPath);
		System.out.println("Temp Foleder   --- " + System.getProperty("java.io.tmpdir"));
		return userName;
	}

	@Override
	public String uploadEmployeeGovernmentIdImage(String userName, MultipartFile imageFile) throws IOException {
		Employee emp = empRepo.findByEmployeeCredentialsUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("InValid Employee Id !!!!! Employee Does Not Exists"));
		String targetPath = employeeGovernmentIdFolder + File.separator + "gi" + emp.getEmployeeId() + "."
				+ imageFile.getOriginalFilename().split("\\.")[1];
		System.out.println("Employee Profile Image Path : " + targetPath);
		Files.copy(imageFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
		emp.setGovernmentIdImagePath(targetPath);
		return userName;
	}

	@Override
	public String uploadApplicantGovernmentIdImage(String userName, MultipartFile imageFile,GovIdDto govId) throws IOException {
		Applicant app = appRepo.findByApplicantCredentialsUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("InValid applicant Id !!!!!"));
		String targetPath = applicantGovernmentIdFolder + File.separator + "gi" + app.getApplicantId() + "."
				+ imageFile.getOriginalFilename().split("\\.")[1];
		System.out.println("Employee Profile Image Path : " + targetPath);
		Files.copy(imageFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
		app.setGovernmentIdImagePath(targetPath);
		app.setGovernmentIdNumber(govId.getGovIdNo());
		app.setGovIdType(govId.getGovType().toString());
		return userName;
	}

	@Override
	public String uploadApplicantProfileImage(String userName, MultipartFile imageFile) throws IOException {
		// TODO Auto-generated method stub
		Applicant app = appRepo.findByApplicantCredentialsUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("InValid applicant Id !!!!! "));
		String targetPath = applicantProfileFolder + File.separator + "pi" + app.getApplicantId() + "."
				+ imageFile.getOriginalFilename().split("\\.")[1];
		System.out.println("Employee Profile Image Path : " + targetPath);
		Files.copy(imageFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
		app.setProfileImagePath(targetPath);

		return userName;
	}

	@Override
	public byte[] getEmployeeProfileImage(String empId) throws IOException {
		Employee emp = empRepo.findByEmployeeCredentialsUserName(empId)
				.orElseThrow(() -> new ResourceNotFoundException("InValid Employee ID"));
		String path = emp.getProfileImagePath();
		if (path == null) {
			throw new ResourceNotFoundException("Image does Not Exists !!!!");
		}
		return Files.readAllBytes(Paths.get(path));
	}

	@Override
	public byte[] getEmployeeGovernmentIdImage(String empId) throws IOException {
		Employee emp = empRepo.findByEmployeeCredentialsUserName(empId)
				.orElseThrow(() -> new ResourceNotFoundException("InValid Employee ID"));
		String path = emp.getGovernmentIdImagePath();
		if (path == null) {
			throw new ResourceNotFoundException("Image does Not Exists !!!!");
		}
		return Files.readAllBytes(Paths.get(path));
	}

	@Override
	public byte[] getApplicantProfileImage(String appId) throws IOException {
		Applicant app = appRepo.findByApplicantCredentialsUserName(appId)
				.orElseThrow(() -> new ResourceNotFoundException("InValid Applicant ID"));
		String path = app.getProfileImagePath();
		if (path == null) {
			throw new ResourceNotFoundException("Image does Not Exists !!!!");
		}
		return Files.readAllBytes(Paths.get(path));
	}

	@Override
	public byte[] getApplicantGovernmentIdImage(String appId) throws IOException {
		Applicant app = appRepo.findByApplicantCredentialsUserName(appId)
				.orElseThrow(() -> new ResourceNotFoundException("InValid Applicant ID"));
		String path = app.getGovernmentIdImagePath();
		if (path == null) {
			throw new ResourceNotFoundException("Image does Not Exists !!!!");
		}
		return Files.readAllBytes(Paths.get(path));
	}

	@Override
	public ApiResponse uploadApplicantResume(String userName, MultipartFile imageFile) throws IOException {
		Applicant app = appRepo.findByApplicantCredentialsUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("InValid applicant Id !!!!! "));
		String targetPath = applicantResumeFolder + File.separator + "re" + app.getApplicantId() + "."
				+ imageFile.getOriginalFilename().split("\\.")[1];
		System.out.println("Employee Profile Image Path : " + targetPath);
		Files.copy(imageFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
		app.setResumePath(targetPath);
		app.setProfileStatus("COMPLETED");
		return new ApiResponse("Documents Uploaded Sucessfully !!!","COMPLETED");

	}

	@Override
	public byte[] getApplicantResume(String userName) throws IOException {
		Applicant app = appRepo.findByApplicantCredentialsUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("InValid Applicant ID"));
		String path = app.getResumePath();
		if (path == null) {
			throw new ResourceNotFoundException("Image does Not Exists !!!!");
		}
		return Files.readAllBytes(Paths.get(path));
	}

}
