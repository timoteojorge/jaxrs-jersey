package br.com.alura.loja.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.alura.loja.modelo.Project;

public class ProjectDAO {

	private static Map<Long, Project> db = new HashMap<Long, Project>();
    private static AtomicLong counter = new AtomicLong(1);

    static {
        db.put(1l, new Project(1l, "Minha loja", 2014));
        db.put(2l, new Project(2l, "Alura", 2012));
    }

    public void add(Project project) {
        long id = counter.incrementAndGet();
        project.setId(id);
        db.put(id, project);
    }

    public Project search(Long id) {
        return db.get(id);
    }

    public Project remove(long id) {
        return db.remove(id);
    }
}
