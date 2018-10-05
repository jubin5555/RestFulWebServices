package controllers;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;



import java.io.*;



class Test {

    public static void main(String[] args ) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        ObjectNode sellOffer1 = mapper.createObjectNode();
        ObjectNode sellOffer2 = mapper.createObjectNode();
        ObjectNode sellOffer3 = mapper.createObjectNode();

        //sellOffer1 values initialized

        sellOffer1.put("offerId","431671cb");
        sellOffer1.put("rate",100);
        sellOffer1.put("amount",5);

        sellOffer2.put("offerId","16b961eb");
        sellOffer2.put("rate",80);
        sellOffer2.put("amount",2);

        sellOffer3.put("offerId","1e06381d");
        sellOffer3.put("rate",50);
        sellOffer3.put("amount",12);

        arrayNode.add(sellOffer1);
        arrayNode.add(sellOffer2);
        arrayNode.add(sellOffer3);

        File file = new File("C:\\Users\\jajubina\\Desktop\\test.json");
        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
        ps.println(arrayNode.toString());


      /*  String
        jsonString = new String(Files.readAllBytes(Paths.get("C:\\Users\\jajubina\\Desktop\\test.json")));
        ps.println(jsonString);
        JsonNode rootNode = mapper.readTree(jsonString);
        ArrayNode Nodes = (ArrayNode) rootNode;
        Iterator<JsonNode> Iterator = Nodes.elements();
        while (Iterator.hasNext()) {
            JsonNode Node = Iterator.next();
            ps.println(Node.get("offerId"));
            ps.println(Node.get("rate"));
            ps.println(Node.get("amount"));
        }*/



    }

}