package codeshare

import com.google.inject.AbstractModule
import com.google.inject.Scopes

class CodeModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CodeService).in(Scopes.SINGLETON)
    }
    
}

