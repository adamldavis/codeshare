package codeshare

import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id
import org.mongodb.morphia.annotations.Indexes
import org.mongodb.morphia.annotations.Index
import org.bson.types.ObjectId

@Entity(value = "code", noClassnameStored = true)
@Indexes(
    [@Index('author'), @Index('-date'), @Index(value='filename, -version', unique = true)]
)
class Code {

    @Id
    ObjectId id
    
    //unique on filename,version
    String filename
    String version
    String author
    String code
    Date date = new Date()
    
}

