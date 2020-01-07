package fr.eni.eniencheres.bll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AfficherImage
 */
@WebServlet("/AfficherImage")
public class AfficherImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO lire les attributs permettant de en request attribute car dans le doGet 
		int idArticle = -1;
		try {
		   idArticle = Integer.parseInt(request.getParameter("idArticle"));
		}catch (Exception e) {
			System.out.println("BAD LOG " + e.getMessage());
		}
		if(idArticle > -1 ) {
		searchAndWriteArticleImageOn(response, idArticle);
		}
		// TODO lire 
        writeDefaultImageOn(response);
	}

	private void searchAndWriteArticleImageOn(HttpServletResponse response, final int idArticle) {
		// TODO Auto-generated method stub
		/*
		 * // Example directory on dos system
       Path dir = Paths.get("c:\\a\\b\\");

        /**
        *
        * Create a new DirectoryStream for the above path. 
        * 
        * List all files within this directory that begin
        * with the letters A or B i.e "[AB)]*"
        * 
        */
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(TraitementArticle.CHEMIN_IMAGES_ARTICLE), String.valueOf(idArticle) + "*"))
        {
            // Print all the files to output stream
            for(Path p: stream)
            {  
                System.out.println(p.getFileName());
                FileInputStream input = new FileInputStream(p.toFile());
                for (int i = input.read(); i>-1; i = input.read()){
        			response.getWriter().write(i);
        		}	
                response.setHeader("Content-Type", "image/" + TraitementArticle.extracted(p.getFileName().toString()));
                break;
            }
        }
        catch(Exception e)
        {
            System.out.println("problems locating directory");
        }


		
	}

	/**
	 * Write the default image on the response
	 * @param response
	 * @throws IOException
	 */
	private void writeDefaultImageOn(HttpServletResponse response) throws IOException {
		InputStream input = this.getClass().getResourceAsStream("default-image_600.png");        
		System.out.println("Use default image");		
		for (int i = input.read(); i>-1; i = input.read()){
			response.getWriter().write(i);
		}		
		//Content-Type: image/png
		response.setHeader("Content-Type", "image/png");
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Ne rien mettre ici
	}

}
