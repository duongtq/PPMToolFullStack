def climbingLeaderboard(ranked, player):
    result = []
    for i in range(0, len(player)):
        result.append(getRank(player[i], ranked))
    return result

def getRank(score, leaderboard):
    leaderboard.append(score)
    leaderboard.sort(reverse=True)
    ranks = [1]
    count = 1;
    for i in range(0, len(leaderboard) - 1):
        if leaderboard[i] == leaderboard[i + 1]:
            ranks.append(count)
        else:
            count+=1
            ranks.append(count)

    return ranks[leaderboard.index(score)]

ranked = [100, 100, 50, 40, 40, 20, 10]
player = [5, 25, 50, 120]

print(climbingLeaderboard(ranked, player))
