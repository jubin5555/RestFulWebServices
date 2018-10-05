package controllers;

import Actors.MarketActor;
import Actors.UserActor;
import com.google.inject.Inject;
import play.libs.Json;
import play.mvc.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import scala.compat.java8.FutureConverters;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

import static akka.pattern.Patterns.ask;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
     final ActorRef UserActor;
     final ActorRef MarketActor;
    @Inject
    public HomeController(ActorSystem system) {
        UserActor= system.actorOf(Actors.UserActor.getProps());
        MarketActor=system.actorOf(Actors.MarketActor.getProps());
    }

    public Result index() {
        return ok(views.html.index.render());
    }

    public CompletionStage<Result> addBalanceUSD(String amount) throws IOException, SQLException {
        return FutureConverters.toJava(ask(UserActor, new UserActor.SetBalance(Integer.parseInt(amount)), 1000))
                .thenApply(response -> ok(String.valueOf(response) ));
    }

    public CompletionStage<Result> getBalance( ) throws SQLException {
        return FutureConverters.toJava(ask(UserActor, new UserActor.CheckBalance(), 1000))
                .thenApply(response -> ok(String.valueOf(response) ));
    }

   public CompletionStage<Result> getTransactions( ) throws IOException, SQLException {
       return FutureConverters.toJava(ask(MarketActor, new MarketActor.GetAllTransactions(), 1000))
               .thenApply(response -> ok(String.valueOf(response) ));
    }

    public CompletionStage<Result> getTransaction(String transactionID ) throws IOException, SQLException {
        return FutureConverters.toJava(ask(MarketActor, new MarketActor.GetDetailOfTransaction(Integer.parseInt(transactionID)), 1000))
                .thenApply(response -> ok(String.valueOf(response) ));
    }


    public CompletionStage<Result> getSellOffers() throws IOException, SQLException {
        return FutureConverters.toJava(ask(MarketActor, new MarketActor.SellOffers(), 1000))
                .thenApply(response -> ok(String.valueOf(response) ));

    }

    public CompletionStage<Result> getSellOffer(String offerid) throws IOException,SQLException {
        return FutureConverters.toJava(ask(MarketActor, new MarketActor.SellOffersById(offerid), 1000))
            .thenApply(response -> ok(String.valueOf(response) ));}

    public CompletionStage<Result> buy(String maxRate,String amount) throws SQLException {
        return FutureConverters.toJava(ask(UserActor, new UserActor.buy(amount,maxRate), 10000))
                .thenApply(response -> ok(String.valueOf(response) ));}


    public CompletionStage<Result> confirmFail() throws IOException, SQLException {
        return FutureConverters.toJava(ask(MarketActor, new MarketActor.ConfirmFail(), 1000))
                .thenApply(response -> ok(String.valueOf(response) ));
    }
    public CompletionStage<Result> confirmNoResponse() throws IOException, SQLException {
        return FutureConverters.toJava(ask(MarketActor, new MarketActor.ConfirmNoResponse(), 1000))
                .thenApply(response -> ok(String.valueOf(response) ));
    }
    public CompletionStage<Result> confirmReset() throws IOException, SQLException {
        return FutureConverters.toJava(ask(MarketActor, new MarketActor.ConfirmReset(), 1000))
                .thenApply(response -> ok(String.valueOf(response) ));
    }
}


