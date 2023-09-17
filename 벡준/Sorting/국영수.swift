let n = Int(readLine()!)!
var tmp: [(Int,Int,Int,String)] = []
for _ in 0..<n {
    let input = readLine()!.split(separator: " ").map{ String($0) }
    tmp.append((Int(input[1])!, Int(input[2])!, Int(input[3])!, input[0]))
}
let ans = tmp.sorted(by: {($0.0,$1.1,$0.2,$1.3) > ($1.0,$0.1,$1.2,$0.3)})
for info in ans {
    print(info.3)
}
