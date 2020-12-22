import heapq

class Solution:
    def findItinerary(self, tickets):
        graph = {}
        for p in tickets:
            if p[0] not in graph:
                ar = []
                heapq.heapify(ar)
                graph[p[0]] = ar
            heapq.heappush(graph[p[0]], p[1])

        result = []
        self.dfs(graph, 'JFK', result)
        return result

    def dfs(self, graph, cur, result):
        while cur in graph and len(graph[cur]) > 0:
            self.dfs(graph, heapq.heappop(graph[cur]), result)
        result.insert(0, cur)


print(Solution().findItinerary([["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]))
# ["JFK", "MUC", "LHR", "SFO", "SJC"]
print(Solution().findItinerary([["JFK","SFO"], ["JFK","ATL"], ["SFO","ATL"], ["ATL","JFK"], ["ATL","SFO"]]))
# ["JFK", "ATL", "JFK", "SFO", "ATL", "SFO"]
print(Solution().findItinerary([["JFK","KUL"], ["JFK","NRT"], ["NRT","JFK"]]))
# ["JFK", "NRT", "JFK", "KUL"]