package com.dag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hp.hpl.jena.ontology.OntModel;
import com.queryprocess.ProcessAnswer;
import com.qureyprocess.components.Action;
import com.qureyprocess.components.Condition;
import com.qureyprocess.components.action.semanticroles.Theme;

public class DAG
{
	Graph DAG;
	public DAG()
	{
		DAG = new Graph();
	}
	@SuppressWarnings({ "rawtypes" })
	public void printDAG()
	{
		//System.out.println();
		Iterator<Entry<String, Struct>> it = DAG.graph.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        System.out.print(pairs.getKey() + " = ");
	        Struct data = (Struct) pairs.getValue();
	        Iterator<String> itset = data.set.iterator();
	        while(itset.hasNext())
	        	System.out.print(itset.next() + " ");
	        System.out.println();
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    //System.out.println();
	}
	public void fillDAGAction(String folder, String foldertmp, String action, OntModel m) throws IOException, InterruptedException
	{
		if(DAG.explored(action))
			return;
		//System.out.println(action);
		DAG.addNode(action);
		DAG.addClass(action, "action");
		//get precondition
		List<String> lspcond = ProcessAnswer.getAnswer(Condition.getPrecondition(folder, foldertmp, action), m);
		Iterator<String> iterpcond = lspcond.iterator();
		//System.out.println("here");
		while(iterpcond.hasNext())
		{
			//String cond = iterpcond.next();
			String str = iterpcond.next();
			//System.out.println(action + " " + str);
			DAG.addNode(str);
			DAG.addLink(str, action);
			System.out.println(str + " ispreconditionof " + action);
			fillDAGPreCondition(folder, foldertmp, str, m);
		}

		//get subaction
		List<String> lssubact = ProcessAnswer.getAnswer(Action.getSubaction(folder, foldertmp, action), m);
		Iterator<String> itersubact = lssubact.iterator();
		while(itersubact.hasNext())
		{
			String str = itersubact.next();
			//String str = acts.substring(acts.indexOf("#")+1);
			//System.out.println(action + " " + str);
			DAG.addNode(str);
			DAG.addLink(str, action);
			System.out.println(str + " issubactionof " + action);
			fillDAGAction(folder, foldertmp, str, m);
		}

		//get outcome
		List<String> lsocom = ProcessAnswer.getAnswer(Condition.getOutcome(folder, foldertmp, action), m);
		Iterator<String> iterocom = lsocom.iterator();
		while(iterocom.hasNext())
		{
			String str = iterocom.next();
			//String str = cond.substring(cond.indexOf("#")+1);
			//System.out.println(action + " " + str);
			DAG.addNode(str);
			DAG.addLink(action, str);
			System.out.println(str + " isoutcomeof " + action);
			fillDAGOutcome(folder, foldertmp, str, m);
		}
	}
	
	
	private void fillDAGPreCondition(String folder, String foldertmp, String condition, OntModel m) throws IOException, InterruptedException
	{
		if(DAG.explored(condition))
			return;
		//System.out.println(condition);
		DAG.addNode(condition);
		DAG.addClass(condition, "precondition");
		List<String> lsoutcome = ProcessAnswer.getAnswer(Action.getoutAction(folder, foldertmp, condition), m);
		Iterator<String> iterocome = lsoutcome.iterator();
		while(iterocome.hasNext())
		{
			String str = iterocome.next();
			//String str = acts.substring(acts.indexOf("#")+1);
			DAG.addNode(str);
			DAG.addLink(str, condition);
			System.out.println(str + " hasprecondition " + condition);
			fillDAGAction(folder, foldertmp, str, m);
		}
	}
	
	private void fillDAGOutcome(String folder, String foldertmp, String condition, OntModel m) throws IOException, InterruptedException
	{
		if(DAG.explored(condition))
			return;
		//System.out.println(condition);
		DAG.addNode(condition);
		DAG.addClass(condition, "outcome");
		List<String> lsprecome = ProcessAnswer.getAnswer(Action.getpreAction(folder, foldertmp, condition), m);
		Iterator<String> iterpcond = lsprecome.iterator();
		while(iterpcond.hasNext())
		{
			String str = iterpcond.next();
			//String str = acts.substring(acts.indexOf("#")+1);
			DAG.addNode(str);
			DAG.addLink(condition, str);
			System.out.println(condition + " isoutcomeof  " + str);
			fillDAGAction(folder, foldertmp, str, m);
		}
	}
	
	
	public ArrayList<String> topologicalsort() {
		ArrayList<String> topo = new ArrayList<String>();
		dfs(topo);
		return topo;
	}
	int time = 0;
	private void dfs(ArrayList<String> topo) {
		Iterator<Entry<String, Struct>> it = DAG.graph.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, Struct> pairs = (Map.Entry<String, Struct>)it.next();
	        Struct data = pairs.getValue();
	        if(data.color == 0)
	        	dfsVisit(pairs.getKey(), topo);
	    }
	}
	private void dfsVisit(String node, ArrayList<String> topo) {
		time++;
		DAG.graph.get(node).color = 1;
		Iterator<String> itset = DAG.graph.get(node).set.iterator();
        while(itset.hasNext())
        {
        	String nei = itset.next();
        	if(DAG.graph.get(nei).color == 0)
        	{
        		DAG.graph.get(nei).prev = node;
        		dfsVisit(nei, topo);
        	}
        }
        time++;
        DAG.graph.get(node).fTime = time;
        topo.add(0, node);
	}
	public static void actionOrder(String folder, String foldertmp, OntModel m, DAG dag, ArrayList<String> topo) throws IOException, InterruptedException {
		for(int i = 0; i < topo.size(); i++)
			if(dag.DAG.getClass(topo.get(i)).equals("action"))
			{
				System.out.print(topo.get(i).replaceAll("\\d", ""));
				findTheme(folder, foldertmp, m, topo.get(i));
				System.out.println();
			}
	}
	public static void totalOrder(String folder, String foldertmp, OntModel m, DAG dag, ArrayList<String> topo) throws IOException, InterruptedException {
		for(int i = 0; i < topo.size(); i++)
		{
			if(dag.DAG.getClass(topo.get(i)).equals("action"))
				System.out.print("Did you do ... ");
			else
			{
				System.out.print("Have you got ... ");
				System.out.print(topo.get(i).replaceAll("\\d", ""));
				findTheme(folder, foldertmp, m, topo.get(i));
				System.out.println();
			}
		}
	}
	public static void order(String folder, OntModel m, DAG dag, ArrayList<String> topo) throws IOException, InterruptedException {
		Collections.sort(topo.subList(0, topo.size()));
		for(int i = 0; i < topo.size(); i++)
			System.out.println(topo.get(i));
	}
	public static void artifactorder(String folder, String foldertmp, OntModel m, DAG dag, ArrayList<String> topo) throws IOException, InterruptedException {
		for(int i = 0; i < topo.size(); i++)
			if(!dag.DAG.getClass(topo.get(i)).equals("action"))
			{
				System.out.print(topo.get(i).replaceAll("\\d", ""));
				findTheme(folder, foldertmp, m, topo.get(i));
				System.out.println();
			}
	}
	public static void findTheme(String folder, String foldertmp, OntModel m, String str) throws IOException,
			InterruptedException {
		ArrayList<String> arr = (ProcessAnswer.getAnswer(Theme.getTheme(folder, foldertmp, str), m));
		//System.out.println(arr);
		System.out.print("[");
		recurTheme(folder, foldertmp, m, arr);
		System.out.print("]");
	}
	public static void recurTheme(String folder, String foldertmp, OntModel m, ArrayList<String> arr) throws IOException, InterruptedException {
		for(int j = 0; j < arr.size(); j++)
		{
			System.out.print("[");
			System.out.print(arr.get(j).replaceAll("\\d", ""));
			if(arr.get(j).matches("^.+?\\d$"))
			{
				ArrayList<String> arrtemp = (ProcessAnswer.getAnswer(Theme.getTheme(folder, foldertmp, arr.get(j)), m));
				recurTheme(folder, foldertmp, m, arrtemp);
			}
			System.out.print("]");
			if(j != arr.size()-1)
				System.out.print(", ");
		}
	}
}
