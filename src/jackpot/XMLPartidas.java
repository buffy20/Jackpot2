/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jackpot;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Lou
 */
public class XMLPartidas {

    public static void generarXML(ArrayList<Historial> historialJugadas, String nombreXml) {
        try {
            //Crear un documento en xml para guardar la información de las partidas
            DocumentBuilderFactory generadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentCreator = generadorDocumento.newDocumentBuilder();
            Document document = documentCreator.newDocument();
            //Crear el elemento raíz y pegarlo al documento
            Element root = document.createElement("Jugadas");
            document.appendChild(root);

            for (int i = 0; i < historialJugadas.size(); i++) {

                //Crear cada jugada que engloba la información y pegarlas al elemento "Jugadas"
                Element elementJugada = document.createElement("Jugada");
                root.appendChild(elementJugada);
                Historial jugada = historialJugadas.get(i);

                //Crear los elementos que contienen la fecha y la hora de la partida
                //Pegarle a éste su texto correspondiente y colgarlo de Jugada
                Element elementDate = document.createElement("FechaYHora");
                elementJugada.appendChild(elementDate);
                Node textoFecha = document.createTextNode("" + jugada.getFechaHora());
                elementDate.appendChild(textoFecha);

                //Crear los elementos que contienen el saldo del jugador
                //Pegarle a éste su texto correspondiente y colgarlo de Jugada
                Element elementSaldo = document.createElement("Saldo");
                elementJugada.appendChild(elementSaldo);
                Node textoSaldo = document.createTextNode("" + jugada.getSaldo());
                elementSaldo.appendChild(textoSaldo);

                //Crear los elementos que contienen el depósito de la máquina
                //Pegarle a éste su texto correspondiente y colgarlo de Jugada
                Element elementDeposito = document.createElement("Deposito");
                elementJugada.appendChild(elementDeposito);
                Node textoDeposito = document.createTextNode("" + jugada.getDeposito());
                elementDeposito.appendChild(textoDeposito);

                //Crear los elementos que contienen el premio ganado en la partida
                //Pegarle a éste su texto correspondiente y colgarlo de Jugada
                Element elementPremio = document.createElement("Premio");
                elementJugada.appendChild(elementPremio);
                Node textoPremio = document.createTextNode("" +jugada.getPremio());
                elementPremio.appendChild(textoPremio);
            }

            //Mostrar el contenido del xml
            TransformerFactory generadorTransformador = TransformerFactory.newInstance();
            Transformer transformador = generadorTransformador.newTransformer();
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "3");
            Source origen = new DOMSource(document);
            Result destino = new StreamResult("jugadas.xml");
            transformador.transform(origen, destino);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLPartidas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XMLPartidas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XMLPartidas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
