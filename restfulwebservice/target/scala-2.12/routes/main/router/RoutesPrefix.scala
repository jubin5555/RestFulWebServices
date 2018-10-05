// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/jajubina/Documents/RestFulWebService/restfulwebservice/conf/routes
// @DATE:Thu Oct 04 15:12:33 EDT 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
