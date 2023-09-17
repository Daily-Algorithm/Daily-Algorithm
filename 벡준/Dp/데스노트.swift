func search(name: Int, len: Int) -> Int {
    if name == n { return 0 }
    
    if dp[name][len] != -1 {
        return dp[name][len]
    }
    
    dp[name][len] = (m - len + 1) * (m - len + 1) + search(name: name + 1, len: names[name] + 1)
    
    if len + names[name] <= m {
        dp[name][len] = min(dp[name][len], search(name: name + 1, len: len + names[name] + 1))
    }
    
    return dp[name][len]
}

let t = readLine()!.components(separatedBy: " ").map{Int($0)!}
let (n, m) = (t[0], t[1])
let limit = 2000
var names = Array(repeating: 0, count: limit)
var dp = Array(repeating: Array(repeating: -1, count: limit), count: limit)

for i in 0..<n {
    names[i] = Int(readLine()!)!
}

print(search(name: 0, len: 0))
