package codeshare

import com.google.inject.Inject
import com.mongodb.WriteConcern
import org.mongodb.morphia.Morphia
import org.mongodb.morphia.Datastore
import com.mongodb.Mongo
import org.bson.types.ObjectId

class CodeService {

    private Datastore datastore


    CodeService() {
        Morphia morphia = new Morphia()
        morphia.mapPackage("codeshare")

        Mongo mongo = new Mongo()
        datastore = morphia.createDatastore(mongo, "codeshare")
    }

    Code getCode(def id) {
        datastore.get(Code, new ObjectId(id))
    }
    
    Code getCode(String f, String v) {
        def query = datastore.createQuery(Code).
            filter("filename =", f).filter("version =", v);
        query.get()
    }

    String saveCode(Code code) {
        datastore.save(code, WriteConcern.SAFE)
        return code.id.toString()
    }
    
    List findByFilename(String f) {
        datastore.createQuery(Code).filter("filename =", f).asList()
    }
    
    Code findLatest(String f) {
        datastore.createQuery(Code).filter("filename =", f).order("-version").get()
    }
    
    List findByAuthor(String a) {
        datastore.createQuery(Code).filter("author =", a).asList()
    }
    
    List recent(int max) {
        datastore.createQuery(Code).order("-date").limit(max).asList()
    }


}

