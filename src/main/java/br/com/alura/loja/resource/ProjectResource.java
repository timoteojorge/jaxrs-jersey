package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.ProjectDAO;
import br.com.alura.loja.modelo.Project;

@Path("projects")
public class ProjectResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")	
	public String search(@PathParam("id") Long id){
		Project project = new ProjectDAO().search(id);
		return project.toXML();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public Response add(String content){
		Project project = (Project) new XStream().fromXML(content);
		new ProjectDAO().add(project);
		URI uri = URI.create("/projects/" + project.getId());

		return Response.created(uri).build();
	}
	
	@Path("{id}")
	@DELETE
	public Response removeProduct(@PathParam("id") Long id){
		
		new ProjectDAO().remove(id);
		return Response.ok().build();
		
	}

}
