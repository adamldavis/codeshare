
import static ratpack.groovy.Groovy.groovyTemplate
import static ratpack.groovy.Groovy.ratpack

import codeshare.Code
import codeshare.CodeService
import codeshare.CodeModule

import ratpack.form.Form

ratpack {
    bindings {
        add new CodeModule()
    }

    handlers { CodeService service ->
        prefix('code') {

            get("i/:id") {
                blocking {
                    service.getCode(pathTokens.id)
                } then { Code code ->
                    render groovyTemplate("code.html", code: code)
                }
            }            
            get("latest/:fn") {
                def fn = pathTokens.fn
                blocking {
                    service.findLatest(fn)
                } then { Code code ->
                    render groovyTemplate("code.html", code: code)
                }
            }
            get("list/:fn") {
                def fn = pathTokens.fn
                blocking {
                    service.findByFilename(fn)
                } then { List list ->
                    render groovyTemplate("list.html", list: list, title: "Code named $fn")
                }
            }
            get("by/:author") {
                def author = pathTokens.author
                blocking {
                    service.findByAuthor(author)
                } then { List list ->
                    render groovyTemplate("list.html", list: list, title: "Code by $author")
                }
            }
            get("get/:fn/:v") {
                blocking {
                    service.getCode(pathTokens.fn, pathTokens.v)
                } then { Code code ->
                    def json = new groovy.json.JsonBuilder()
                    json filename: code.filename, version: code.version,
                        author: code.author, date: code.date, code: code.code
                    render json.toString()
                }
            }
            get("recent") {
                blocking {
                    service.recent(50)
                } then { List list ->
                    render groovyTemplate("list.html", list: list, title: "Recent additions")
                }
            }

            post {
                def form = parse(Form)
                def f = form.get("filename")
                def v = form.get("version")
                def code = service.getCode(f, v)
                if (code) {
                    render groovyTemplate("code.html", code: code, message: 'Given file/version already exists')
                } else {
                    code = new Code(filename: f, version: v,
                                author: form.get("author"),
                                code: form.get("code"));
                    blocking {
                        service.saveCode(code)
                    } then { String id ->
                        render groovyTemplate("code.html", code: service.getCode(id))
                    }
                }
            }

        }

        assets "public", "index.html"
    }
}

