/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
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
import com.queryprocess.components.Action;
import com.queryprocess.components.Condition;
import com.queryprocess.components.action.semanticroles.Theme;

/**
 * The Class DAG.
 */
public class DAG
{
	
	/** The dag. */
	Graph DAG;
	
	/**
	 * Instantiates a new dag.
	 */
	public DAG()
	{
		DAG = new Graph();
	}
	
	/**
	 * Prints the dag.
	 */
	@SuppressWarnings({ "rawtypes" })
	public void printDAG()
	{
		//System.out.println();
		Iterator<Entry<String, Struct>> it = DAG.graph.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        System.out.print(pairs.getKey() + " = "); //$NON-NLS-1$
	        Struct data = (Struct) pairs.getValue();
	        Iterator<String> itset = data.set.iterator();
	        while(itset.hasNext())
	        	System.out.print(itset.next() + " "); //$NON-NLS-1$
	        System.out.println();
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    //System.out.println();
	}
	
	/**
	 * Fill dag action.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param action
	 *            the action
	 * @param m
	 *            the m
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void fillDAGAction(String folder, String foldertmp, String action, OntModel m) throws IOException, InterruptedException
	{
		if(DAG.explored(action))
			return;
		//System.out.println(action);
		DAG.addNode(action);
		DAG.addClass(action, Strings.getString("action")); //$NON-NLS-1$
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
			System.out.println(str + Strings.getString("preconditionof") + action); //$NON-NLS-1$
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
			System.out.println(str + Strings.getString("subactionof") + action); //$NON-NLS-1$
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
			System.out.println(str + Strings.getString("outcomeof") + action); //$NON-NLS-1$
			fillDAGOutcome(folder, foldertmp, str, m);
		}
	}
	
	
	/**
	 * Fill dag pre condition.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param condition
	 *            the condition
	 * @param m
	 *            the m
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	private void fillDAGPreCondition(String folder, String foldertmp, String condition, OntModel m) throws IOException, InterruptedException
	{
		if(DAG.explored(condition))
			return;
		//System.out.println(condition);
		DAG.addNode(condition);
		DAG.addClass(condition, Strings.getString("precondition")); //$NON-NLS-1$
		List<String> lsoutcome = ProcessAnswer.getAnswer(Action.getoutAction(folder, foldertmp, condition), m);
		Iterator<String> iterocome = lsoutcome.iterator();
		while(iterocome.hasNext())
		{
			String str = iterocome.next();
			//String str = acts.substring(acts.indexOf("#")+1);
			DAG.addNode(str);
			DAG.addLink(str, condition);
			System.out.println(str + Strings.getString("hasprecondition") + condition); //$NON-NLS-1$
			fillDAGAction(folder, foldertmp, str, m);
		}
	}
	
	/**
	 * Fill dag outcome.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param condition
	 *            the condition
	 * @param m
	 *            the m
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	private void fillDAGOutcome(String folder, String foldertmp, String condition, OntModel m) throws IOException, InterruptedException
	{
		if(DAG.explored(condition))
			return;
		//System.out.println(condition);
		DAG.addNode(condition);
		DAG.addClass(condition, Strings.getString("hasoutcome")); //$NON-NLS-1$
		List<String> lsprecome = ProcessAnswer.getAnswer(Action.getpreAction(folder, foldertmp, condition), m);
		Iterator<String> iterpcond = lsprecome.iterator();
		while(iterpcond.hasNext())
		{
			String str = iterpcond.next();
			//String str = acts.substring(acts.indexOf("#")+1);
			DAG.addNode(str);
			DAG.addLink(condition, str);
			System.out.println(condition + Strings.getString("isoutcome") + str); //$NON-NLS-1$
			fillDAGAction(folder, foldertmp, str, m);
		}
	}
	
	
	/**
	 * Topologicalsort.
	 * 
	 * @return the array list
	 */
	public ArrayList<String> topologicalsort() {
		ArrayList<String> topo = new ArrayList<String>();
		dfs(topo);
		return topo;
	}
	
	/** The time. */
	int time = 0;
	
	/**
	 * Dfs.
	 * 
	 * @param topo
	 *            the topo
	 */
	private void dfs(ArrayList<String> topo) {
		Iterator<Entry<String, Struct>> it = DAG.graph.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, Struct> pairs = (Map.Entry<String, Struct>)it.next();
	        Struct data = pairs.getValue();
	        if(data.color == 0)
	        	dfsVisit(pairs.getKey(), topo);
	    }
	}
	
	/**
	 * Dfs visit.
	 * 
	 * @param node
	 *            the node
	 * @param topo
	 *            the topo
	 */
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
	
	/**
	 * Action order.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param m
	 *            the m
	 * @param dag
	 *            the dag
	 * @param topo
	 *            the topo
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void actionOrder(String folder, String foldertmp, OntModel m, DAG dag, ArrayList<String> topo) throws IOException, InterruptedException {
		for(int i = 0; i < topo.size(); i++)
			if(dag.DAG.getClass(topo.get(i)).equals(Strings.getString("action"))) //$NON-NLS-1$
			{
				System.out.print(topo.get(i).replaceAll(Strings.getString("digitRegex"), "")); //$NON-NLS-1$ //$NON-NLS-2$
				findTheme(folder, foldertmp, m, topo.get(i));
				System.out.println();
			}
	}
	
	/**
	 * Total order.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param m
	 *            the m
	 * @param dag
	 *            the dag
	 * @param topo
	 *            the topo
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void totalOrder(String folder, String foldertmp, OntModel m, DAG dag, ArrayList<String> topo) throws IOException, InterruptedException {
		for(int i = 0; i < topo.size(); i++)
		{
			if(dag.DAG.getClass(topo.get(i)).equals(Strings.getString("action"))) //$NON-NLS-1$
				System.out.print(Strings.getString("didyoudomessage")); //$NON-NLS-1$
			else
			{
				System.out.print(Strings.getString("haveyougotmessage")); //$NON-NLS-1$
				System.out.print(topo.get(i).replaceAll(Strings.getString("digitRegex"), "")); //$NON-NLS-1$ //$NON-NLS-2$
				findTheme(folder, foldertmp, m, topo.get(i));
				System.out.println();
			}
		}
	}
	
	/**
	 * Order.
	 * 
	 * @param folder
	 *            the folder
	 * @param m
	 *            the m
	 * @param dag
	 *            the dag
	 * @param topo
	 *            the topo
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void order(String folder, OntModel m, DAG dag, ArrayList<String> topo) throws IOException, InterruptedException {
		Collections.sort(topo.subList(0, topo.size()));
		for(int i = 0; i < topo.size(); i++)
			System.out.println(topo.get(i));
	}
	
	/**
	 * Artifactorder.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param m
	 *            the m
	 * @param dag
	 *            the dag
	 * @param topo
	 *            the topo
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void artifactorder(String folder, String foldertmp, OntModel m, DAG dag, ArrayList<String> topo) throws IOException, InterruptedException {
		for(int i = 0; i < topo.size(); i++)
			if(!dag.DAG.getClass(topo.get(i)).equals(Strings.getString("action"))) //$NON-NLS-1$
			{
				System.out.print(topo.get(i).replaceAll(Strings.getString("digitRegex"), "")); //$NON-NLS-1$ //$NON-NLS-2$
				findTheme(folder, foldertmp, m, topo.get(i));
				System.out.println();
			}
	}
	
	/**
	 * Find theme.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param m
	 *            the m
	 * @param str
	 *            the str
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void findTheme(String folder, String foldertmp, OntModel m, String str) throws IOException,
			InterruptedException {
		ArrayList<String> arr = (ProcessAnswer.getAnswer(Theme.getTheme(folder, foldertmp, str), m));
		//System.out.println(arr);
		System.out.print("["); //$NON-NLS-1$
		recurTheme(folder, foldertmp, m, arr);
		System.out.print("]"); //$NON-NLS-1$
	}
	
	/**
	 * Recur theme.
	 * 
	 * @param folder
	 *            the folder
	 * @param foldertmp
	 *            the foldertmp
	 * @param m
	 *            the m
	 * @param arr
	 *            the arr
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void recurTheme(String folder, String foldertmp, OntModel m, ArrayList<String> arr) throws IOException, InterruptedException {
		for(int j = 0; j < arr.size(); j++)
		{
			System.out.print("["); //$NON-NLS-1$
			System.out.print(arr.get(j).replaceAll(Strings.getString("digitRegex"), "")); //$NON-NLS-1$ //$NON-NLS-2$
			if(arr.get(j).matches(Strings.getString("instanceRegex"))) //$NON-NLS-1$
			{
				ArrayList<String> arrtemp = (ProcessAnswer.getAnswer(Theme.getTheme(folder, foldertmp, arr.get(j)), m));
				recurTheme(folder, foldertmp, m, arrtemp);
			}
			System.out.print("]"); //$NON-NLS-1$
			if(j != arr.size()-1)
				System.out.print(", "); //$NON-NLS-1$
		}
	}
}
