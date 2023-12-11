package edu.vku.searchjob.service;

public interface IEmailService {
    public void sendEmail(String to, String subject, String body);
}
