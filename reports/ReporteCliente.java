package reports;

import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import models.Cliente;
import models.Cuenta;

/**
 * ReporteCliente
 */
public class ReporteCliente {
    public void generarReporte(ArrayList<Cliente> clientes) {
        Document documento = new Document();

        try {
            FileOutputStream archivoPdf = new FileOutputStream("ReporteDeClientes.pdf");
            PdfWriter.getInstance(documento, archivoPdf).setInitialLeading(20);
            documento.open();
            Paragraph titulo = new Paragraph("Reporte de Clientes y Cuentas");
            titulo.setAlignment(1);
            documento.add(titulo);
            documento.add(new Paragraph("\n"));
            
            for (Cliente cliente : clientes) {
                documento.add(new LineSeparator());
                documento.add( new Paragraph(cliente.ObtenerDatosCliente()));
                documento.add(new Paragraph("\n")); 
                documento.add(new LineSeparator());
                documento.add(new Paragraph("Cuentas")); 
                documento.add(obtenerDatosCuentasCliente(cliente.getCuentasCliente()));  
                documento.add(new LineSeparator());
            }

            documento.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Paragraph obtenerDatosCuentasCliente(ArrayList<Cuenta> cuentasCliente) {
        Paragraph datosCuenta = new Paragraph();       
        datosCuenta.setSpacingBefore(10); 
        datosCuenta.setSpacingAfter(20);

        for (Cuenta cuenta : cuentasCliente) {
            datosCuenta.add(cuenta.obtenerInformacionCuenta());
        }

        return datosCuenta;

    }
}