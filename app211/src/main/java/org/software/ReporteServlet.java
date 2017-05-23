package org.software;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class ReporteServlet
 */
public class ReporteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReporteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
            // Get the text that will be added to the PDF
            String text = request.getParameter("text");
            if (text == null || text.trim().length() == 0) {
                 text = "You didn't enter any text.";
            }
            // step 1
            Document document = new Document();
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            // step 3
            document.open();
            // step 4
            /*document.add(new Paragraph(String.format(
                "You have submitted the following text using the %s method:",
                request.getMethod())));
            document.add(new Paragraph(text));
            */
            
            
            String ruta = this.getServletContext().getRealPath("/imagenes/imagen1.jpg");
            Image image1 = Image.getInstance(ruta);
            document.add(image1);
            
            PdfPTable table = new PdfPTable(3);
            // the cell object
            PdfPCell cell;
            // we add a cell with colspan 3
            cell = new PdfPCell(new Phrase("Cell with colspan 3"));
            cell.setColspan(3);
            table.addCell(cell);
            // now we add a cell with rowspan 2
            cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
            cell.setRowspan(2);
            table.addCell(cell);
            // we add the four remaining cells with addCell()
            table.addCell("row 1; cell 1");
            table.addCell("row 1; cell 2");
            table.addCell("row 2; cell 1");
            table.addCell("row 2; cell 2");
                    
            document.add(table);
            
            // step 5
            document.close();
 
            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        }
        catch(DocumentException e) {
            throw new IOException(e.getMessage());
        }
    	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
