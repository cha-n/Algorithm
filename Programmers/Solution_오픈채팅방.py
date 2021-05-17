act = dict()
nickname = dict()
act["Enter"] = 1
act["Leave"] = 2
print(act.get("Enter"))


record = ["Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"]
history = []
result = []
for i in range(len(record)):
    temp = record[i].split(" ")
    print(temp)
    print(temp[0], temp[1])
    if temp[0]=="Enter":
        history.append((act.get(temp[0]), temp[1]))
        print("Enter")
        nickname[temp[1]] = temp[2]
    elif temp[0]=="Leave":
        print("Leave")
        history.append((act.get(temp[0]), temp[1]))
    else:
        nickname[temp[1]] = temp[2]
    print(history, nickname)

for i in range(len(history)):
    if history[i][0]==1:
        result.append(nickname.get(history[i][1])+"님이 들어왔습니다.")
    else:
        result.append(nickname.get(history[i][1]) + "님이 나갔습니다.")

print(result)
