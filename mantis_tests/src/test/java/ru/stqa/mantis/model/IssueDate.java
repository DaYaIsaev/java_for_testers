package ru.stqa.mantis.model;

public record IssueDate(String summary, String description, Long project, Long category) {

    public IssueDate(){
        this("","",1L,1L);
    }

    public IssueDate withSummary(String summary){
        return  new IssueDate(summary, this.description, this.project, this.category);
    }

    public IssueDate withDescription(String description){
        return  new IssueDate(this.summary, description, this.project, this.category);
    }

    public IssueDate withProject(Long project){
        return  new IssueDate(this.summary, this.description, project, this.category);
    }

    public IssueDate withCategory(Long category){
        return  new IssueDate(this.summary, this.description, this.project, category );
    }


}
