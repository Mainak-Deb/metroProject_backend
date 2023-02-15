package com.mainak.functions;

import java.util.*;


public class MetroGraph {
    private TreeMap<String, HashSet<String>> graph = new TreeMap<String, HashSet<String>>();
    private HashSet<String>  terminalStations = new HashSet<String>();

    public MetroGraph(ArrayList<String[]> data){

        for(String[] line : data){
            if(line[2].equals("Y")){
                terminalStations.add(line[0]);
            }

            //Insert the node for line[0]
            if(!graph.containsKey(line[0])){
                graph.put(line[0], new HashSet<String>());
                graph.get(line[0]).add(line[1]);
            }else{
                graph.get(line[0]).add(line[1]);
            }

            //Insert the node for line[1]
            if(!graph.containsKey(line[1])){
                graph.put(line[1], new HashSet<String>());
                graph.get(line[1]).add(line[0]);
            }else{
                graph.get(line[1]).add(line[0]);
            }
            
        }

        // for(String key : graph.keySet()){
        //     System.out.println(key+" : "+graph.get(key));
        // }
        //System.out.println("Terminal Stations : "+terminalStations);
    }

    public ArrayList<String[]> bfsShortestPath(String start, String end){
        ArrayList<String[]> shortestPath = new ArrayList<String[]>();
        ArrayList<String> queue = new ArrayList<String>();
        ArrayList<ArrayList<String>> queueList = new ArrayList<ArrayList<String>>();
        ArrayList<String> nodeList = new ArrayList<String>();

        queue.add(start);
        nodeList.add(start);
        queueList.add(nodeList);
        while(!queue.isEmpty()){
            String node = queue.remove(0);
            nodeList = queueList.remove(0);

            if(node.equals(end)){
                //nodeList.add(node);
                break;
            }
            for(String neighbor : graph.get(node)){
                if(!nodeList.contains(neighbor)){
                    queue.add(neighbor);
                    ArrayList<String> newnodeList = new ArrayList<>(nodeList);
                    newnodeList.add(neighbor);
                    queueList.add(newnodeList);
                }
            }
        }
        //System.out.println(nodeList);
        for(String station: nodeList){
            if(!terminalStations.contains(station)){
                shortestPath.add(
                    new String[]{station,"N"}
                );
            }else{
                shortestPath.add(
                    new String[]{station,"Y"}
                );
            }
        }
        
        return shortestPath;
    }

}
