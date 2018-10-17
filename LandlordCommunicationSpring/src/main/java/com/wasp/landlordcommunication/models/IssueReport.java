package com.wasp.landlordcommunication.models;

import com.wasp.landlordcommunication.utils.Constants;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Constants.ISSUE_REPORTS_TABLE_NAME)
public class IssueReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.ISSUE_REPORTS_ID_COLUMN_NAME)
    private int issueId;

    @Column(name = Constants.ISSUE_REPORTS_USER_ID_COLUMN_NAME)
    private int userId;

    @Column(name = Constants.ISSUE_REPORTS_ISSUE_STATUS_COLUMN_NAME)
    private String issueStatus;

    @Column(name = Constants.ISSUE_REPORTS_PROPERTY_ID_COLUMN_NAME)
    private int propertyId;

    @Column(name = Constants.ISSUE_REPORTS_ISSUE_TEXT_COLUMN_NAME)
    private String issueReportText;

    @Column(name = Constants.ISSUE_REPORTS_ISSUE_DATE_COLUMN_NAME)
    private Date issueDate;

    @ManyToOne
    @JoinColumn(name = Constants.USERS_TABLE_ID_COLUMN_NAME, insertable = false, updatable = false)
    private User user;

    public IssueReport() {

    }

    public IssueReport(int userId, String issueStatus, int propertyId, String issueReportText, Date issueDate) {
        setUserId(userId);
        setIssueStatus(issueStatus);
        setPropertyId(propertyId);
        setIssueReportText(issueReportText);
        setIssueDate(issueDate);
    }

    public int getIssueId() {
        return issueId;
    }

    public int getUserId() {
        return userId;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public String getIssueReportText() {
        return issueReportText;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public User getUser() {
        return user;
    }

    private void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    private void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    private void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    private void setIssueReportText(String issueReportText) {
        this.issueReportText = issueReportText;
    }

    private void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}
