package common.models;
import lombok.Data;
import lombok.Getter;

@Data
public class PersonalDataModel {
    public String firstName;
    public String lastName;
    public String businessEmail;
    public Industry industry;
    public String jobTitle;
    public String companyName;
    public String phone;
    public String howDidYouHearAboutUs;

    @Getter
    public enum Industry {
        retail("Retails"),
        fashion("Fashion"),
        beautyAndCosmetics("Beauty & Cosmetics"),
        foodAndDelivary("Food & Delivery"),
        consumerElectronics("Consumer Electronics"),
        financialServices("Financial Services"),
        media("Media"),
        telco("Telco"),
        automotive("Automotive"),
        eBetting("eBetting"),
        travel("Travel"),
        luxury("Luxury"),
        other("Other");

        final String value;

        Industry(String value) {
            this.value = value;
        }

    }

}
