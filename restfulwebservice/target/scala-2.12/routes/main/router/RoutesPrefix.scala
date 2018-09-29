// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/jajubina/Documents/RestFulWebService/restfulwebservice/conf/routes
// @DATE:Sat Sep 29 01:36:15 EDT 2018


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
