// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/jajubina/Documents/RestFulWebService/restfulwebservice/conf/routes
// @DATE:Thu Oct 04 15:12:33 EDT 2018

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:22
    def confirmFail(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "debug/confirm_fail")
    }
  
    // @LINE:18
    def getSellOffer(offerid:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "selloffers/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("offerid", offerid)))
    }
  
    // @LINE:20
    def buy(maxrate:String, amount:String): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "buy/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("maxrate", maxrate)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("amount", amount)))
    }
  
    // @LINE:12
    def getTransactions(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "transactions")
    }
  
    // @LINE:10
    def getBalance(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "getbalance")
    }
  
    // @LINE:24
    def confirmNoResponse(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "debug/confirm_no_response")
    }
  
    // @LINE:14
    def getTransaction(transactionID:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "transactions/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("transactionID", transactionID)))
    }
  
    // @LINE:26
    def confirmReset(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "debug/reset")
    }
  
    // @LINE:8
    def addBalanceUSD(amount:String): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "addbalance/usd/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("amount", amount)))
    }
  
    // @LINE:16
    def getSellOffers(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "selloffers")
    }
  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:28
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:28
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
