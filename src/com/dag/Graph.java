/*
 * @author	Rishabh Srivastava
 * @organization	IIIT Hyderabad
 */
package com.dag;

import java.util.HashMap;

/**
 * The Class Graph.
 */
public class Graph {

	//public int numNodes = 0;
	/** The graph. */
	public HashMap<String, Struct> graph;

	/**
	 * Instantiates a new graph.
	 */
	Graph()
	{
		graph = new HashMap<String, Struct>();
	}
	
	/**
	 * Exist node.
	 * 
	 * @param node
	 *            the node
	 * @return true, if successful
	 */
	public boolean existNode(String node)
	{
		if(graph.get(node) == null)
			return false;
		return true;
	}
	
	/**
	 * Adds the node.
	 * 
	 * @param node
	 *            the node
	 */
	public void addNode(String node)
	{
		if(graph.get(node) == null)
		{
			Struct data = new Struct();
			graph.put(node, data);
		}
	}
	
	/**
	 * Adds the link.
	 * 
	 * @param node1
	 *            the node1
	 * @param node2
	 *            the node2
	 */
	public void addLink(String node1, String node2)
	{
		Struct data = graph.get(node1);
		data.set.add(node2);
		graph.put(node1, data);
	}
	
	/**
	 * Adds the class.
	 * 
	 * @param node
	 *            the node
	 * @param clas
	 *            the clas
	 */
	public void addClass(String node, String clas)
	{
		graph.get(node).clas = clas;
	}
	
	/**
	 * Gets the class.
	 * 
	 * @param node
	 *            the node
	 * @return the class
	 */
	public String getClass(String node)
	{
		return graph.get(node).clas;
	}
	
	/**
	 * Explored.
	 * 
	 * @param node
	 *            the node
	 * @return true, if successful
	 */
	public boolean explored(String node) {
		if(!existNode(node))
			return false;
		if(graph.get(node).iTime == -1)
		{
			graph.get(node).iTime = 1;
			return false;
		}
		else
			return true;
	}
}
