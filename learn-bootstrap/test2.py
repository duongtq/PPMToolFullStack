def climbingLeaderboard(ranked, player):
    uniqueScores = set()
    rankMap = {}

    rank = 1;
    for i in range(0, len(ranked)):
        if ranked[i] not in uniqueScores:
            uniqueScores.add(ranked[i])
            rankMap[rank] = ranked[i]
            rank+=1
        
    result = []
    
    for i in range(0, len(player)):
        x_rank = 1
        for rank, score in rankMap.items():
            if player[i] < score:
                x_rank+=1 
        result.append(x_rank)

    return result

# ranked = [100, 100, 50, 40, 40, 20, 10]
# player = [5, 25, 50, 120]

ranked = [100, 90, 90, 80, 75, 60]

player = [50, 65, 77, 90, 102]

print(climbingLeaderboard(ranked, player))
