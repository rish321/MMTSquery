package com.recipe.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.Template.TemplatePrepare;
import com.dag.DAG;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class Test {

	public static void main(String args[]) throws IOException, InterruptedException
	{
		String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
		String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
		String owl = "http://www.w3.org/2002/07/owl#";
		String xsd = "http://www.w3.org/2001/XMLSchema#";
		String base = "http://www.semanticweb.org/luffy/ontologies/2014/2/PurposeNetFoodRecipeOntology#";
		
		String folder = "/home/rishabh/tools/pellet-2.3.1/recipe/";
		String foldertmp = ".tmp";
		
		TemplatePrepare.preProcess("/home/rishabh/tools/pellet-2.3.1/", rdfs, rdf, owl, xsd, base, folder, "/home/rishabh/tools/pellet-2.3.1/unprocessed");
		
		OntModel m = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
		String	ontology = "/home/rishabh/ontologies/recipe/third.rdf";
		m.read( ontology );
		
		String actions[] = {"prepare1", "prepare2", "prepare3", "prepare4", "prepare5", "prepare6", "prepare7", "prepare8", "prepare9", "prepare10"};
		ArrayList<ArrayList<String>> arraylists = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < actions.length; i++)
		{
			arraylists.add(topo(folder, foldertmp, m, actions[i]));
		}
		
		/*HashMap<String, Integer> hm = new HashMap<String, Integer>();
		
		for(int i = 0; i < actions.length; i++)
		{
			for(int j = 0; j < arraylists.get(i).size(); j++)
			{
				if(hm.get(arraylists.get(i).get(j)) == null)
					hm.put(arraylists.get(i).get(j), 1);
				else
					hm.put(arraylists.get(i).get(j), hm.get(arraylists.get(i).get(j))+1);
			}
		}
		
		Iterator it = hm.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			//System.out.println(pairs.getKey() + " " + pairs.getValue());
			it.remove();
		}*/
		
		/*List<String> topo12 = intersection(topo1, topo2);
		List<String> topo23 = intersection(topo2, topo3);
		List<String> topo13 = intersection(topo1, topo3);
		List<String> topo24 = intersection(topo2, topo4);
		List<String> topo34 = intersection(topo3, topo4);
		List<String> topo14 = intersection(topo1, topo4);
		List<String> topo15 = intersection(topo1, topo5);
		List<String> topo25 = intersection(topo2, topo5);
		List<String> topo35 = intersection(topo3, topo5);
		List<String> topo45 = intersection(topo4, topo5);
		
		System.out.println("12345 " + intersection(intersection(topo12, topo3), topo45));
		
		System.out.println("1234 " + intersection(topo12, topo34));
		System.out.println("1235 " + intersection(topo12, topo35));
		System.out.println("1245 " + intersection(topo12, topo45));
		System.out.println("1345 " + intersection(topo13, topo45));
		System.out.println("2345 " + intersection(topo23, topo45));
		
		System.out.println("123 " + intersection(topo12, topo3));
		System.out.println("234 " + intersection(topo23, topo4));
		System.out.println("134 " + intersection(topo34, topo1));
		System.out.println("234 " + intersection(topo23, topo4));
		System.out.println("124 " + intersection(topo14, topo2));
		System.out.println("231 " + intersection(topo23, topo1));
		System.out.println("135 " + intersection(topo13, topo5));
		
		System.out.println("12 " + intersection(topo1, topo2));
		System.out.println("23 " + intersection(topo2, topo3));
		System.out.println("13 " + intersection(topo3, topo1));
		System.out.println("24 " + intersection(topo4, topo2));
		System.out.println("34 " + intersection(topo4, topo3));
		System.out.println("14 " + intersection(topo4, topo1));
		System.out.println("15 " + intersection(topo1, topo5));
		System.out.println("25 " + intersection(topo2, topo5));
		System.out.println("35 " + intersection(topo3, topo5));
		System.out.println("45 " + intersection(topo4, topo5));
		
		System.out.println("1 " + topo1);
		System.out.println("2 " + topo2);
		System.out.println("3 " + topo3);
		System.out.println("4 " + topo4);
		System.out.println("5 " + topo5);*/
	}

    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
	
	
	public static ArrayList<String> topo(String folder, String foldertmp, OntModel m, String action)
			throws IOException, InterruptedException {
		DAG dag;
		dag = new DAG();
		ArrayList<String> topo = createDag(folder, foldertmp, m, action, dag);
		//DAG.order(folder, m, dag, topo);
		//DAG.totalOrder(folder, m, dag, topo);
		DAG.artifactorder(folder, foldertmp, m, dag, topo);
		System.out.println();
		return topo;
	}

	public static ArrayList<String> createDag(String folder, String foldertmp, OntModel m,
			String action, DAG dag) throws IOException, InterruptedException {
		dag.fillDAGAction(folder, foldertmp, action, m);
		ArrayList<String> topo = dag.topologicalsort();
		return topo;
	}
}
