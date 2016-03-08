package salazar.snapclicker;

/**
 * Created by Salazar on 20-02-2016.
 */
public class QueryBuilder {


        /**
         * Specify your database name here
         * @return
         */
        public String getDatabaseName() {
            return "clicker";
        }

        /**
         * Specify your MongoLab API here
         * @return
         */
        public String getApiKey() {
            return "-AaauYWnzQ_I5jnBy4VFftfM2thjT17r";
        }

        /**
         * This constructs the URL that allows you to manage your database,
         * collections and documents
         * @return
         */
        public String getBaseUrl()
        {
            return "https://api.mongolab.com/api/1/databases/"+getDatabaseName()+"/collections/";
        }

        /**
         * Completes the formatting of your URL and adds your API key at the end
         * @return
         */
        public String docApiKeyUrl()
        {
            return "?apiKey="+getApiKey();
        }

        /**
         * Returns the docs101 collection
         * @return
         */
        public String documentRequest()
        {
            return "students";
        }

        /**
         * Builds a complete URL using the methods specified above
         * @return
         */
        public String buildContactsSaveURL()
        {
            return getBaseUrl()+documentRequest()+docApiKeyUrl();
        }

        /**
         * Formats the contact details for MongoHQ Posting
         * @param student: Details of the person
         * @return
         */
        public String createContact(Students student)
        {
            return String
                    .format("{\"document\" : {\"first_name\": \"%s\", "
                                    + "\"last_name\": \"%s\", \"email\": \"%s\", "
                                    + "\"phone\": \"%s\"}, \"safe\" : true}",
                            student.first_name, student.last_name, student.email, student.phone);
        }

}

