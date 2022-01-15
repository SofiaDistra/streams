package nummerical.summarization;

// based on data from: http://archive.ics.uci.edu/ml/datasets/Bank+Marketing#
public class Record {

    private Integer age;
    private String job;
    private String marital;
    private String education;
    private Boolean isDefaultCredit;
    private Boolean housing;
    private Boolean loan;
    private String contact;
    private String month;
    private String dayOfWeek;
    private Integer duration;
    private Integer campaign;
    private Integer pdays;
    private Integer previous;
    private String poutcome;
    private Double empVarRate;
    private Double consPriceIdx;
    private Double consConfIdx;
    private Double euribor3m;
    private Integer numOfEmployees;
    private Boolean subscribe;

    public Record(String[] data) {
        // TODO
    }

    public boolean isNotSubscriber(){
        return subscribe;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Boolean isDefaultCredit() {
        return isDefaultCredit;
    }

    public void setIsDefaultCredit(Boolean isDefaultCredit) {
        this.isDefaultCredit = isDefaultCredit;
    }

    public Boolean isHousing() {
        return housing;
    }

    public void setHousing(Boolean housing) {
        this.housing = housing;
    }

    public Boolean isLoan() {
        return loan;
    }

    public void setLoan(Boolean loan) {
        this.loan = loan;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCampaign() {
        return campaign;
    }

    public void setCampaign(Integer campaign) {
        this.campaign = campaign;
    }

    public Integer getPdays() {
        return pdays;
    }

    public void setPdays(Integer pdays) {
        this.pdays = pdays;
    }

    public Integer getPrevious() {
        return previous;
    }

    public void setPrevious(Integer previous) {
        this.previous = previous;
    }

    public String getPoutcome() {
        return poutcome;
    }

    public void setPoutcome(String poutcome) {
        this.poutcome = poutcome;
    }

    public Double getEmpVarRate() {
        return empVarRate;
    }

    public void setEmpVarRate(Double empVarRate) {
        this.empVarRate = empVarRate;
    }

    public Double getConsPriceIdx() {
        return consPriceIdx;
    }

    public void setConsPriceIdx(Double consPriceIdx) {
        this.consPriceIdx = consPriceIdx;
    }

    public Double getConsConfIdx() {
        return consConfIdx;
    }

    public void setConsConfIdx(Double consConfIdx) {
        this.consConfIdx = consConfIdx;
    }

    public Double getEuribor3m() {
        return euribor3m;
    }

    public void setEuribor3m(Double euribor3m) {
        this.euribor3m = euribor3m;
    }

    public Integer getNumOfEmployees() {
        return numOfEmployees;
    }

    public void setNumOfEmployees(Integer numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }
}
