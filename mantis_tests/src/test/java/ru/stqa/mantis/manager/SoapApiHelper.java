package ru.stqa.mantis.manager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.mantis.model.IssueDate;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;


public class SoapApiHelper extends HelperBase {
    MantisConnectPortType mantis;

    public SoapApiHelper(ApplicationManager manager) {
        super(manager);
        try {
            mantis = new MantisConnectLocator().getMantisConnectPort(
                    new URL(manager.property("soap.endPoint")));
        } catch (ServiceException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    public void createIssue(IssueDate issueDate) {

        try {
            var categories = mantis.mc_project_get_categories(
                    manager.property("web.username"),
                    manager.property("web.password"),
                    BigInteger.valueOf(issueDate.project()));
            var issue = new biz.futureware.mantis.rpc.soap.client.IssueData();
            issue.setSummary(issueDate.summary());
            issue.setDescription(issueDate.description());
            var projectId = new ObjectRef();
            projectId.setId(BigInteger.valueOf(issueDate.project()));
            issue.setProject(projectId);
            issue.setCategory(categories[0]);
            mantis.mc_issue_add(
                    manager.property("web.username"),
                    manager.property("web.password"),
                    issue);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


    }


}
