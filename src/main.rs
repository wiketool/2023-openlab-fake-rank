use std::vec;

use fake::faker::number::zh_cn::*;
use fake::faker::name::zh_cn::*;
use fake::{Dummy, Fake};
use serde::Serialize;
use rand::Rng;
use warp::Filter;

#[derive(Serialize, Clone, PartialEq, Debug, Dummy)]
struct Score {
    level: i32,
    score: i32,
}

#[derive(Serialize, Clone, PartialEq, Debug, Dummy)]
struct Item {
    name: String,
    id: i32,
    score: Vec<Score>,
}

#[tokio::main]
async fn main() {
    // GET /rank => []
    let fake = warp::path!("rank").map(|| {
        let resp = generate();
        warp::reply::json(&resp)
    });

    warp::serve(fake).run(([0,0,0,0], 13030)).await;
}

fn generate() -> Vec<Item> {
    let mut result: Vec<Item> = Vec::new();
    for _i in 0..20 {
        let mut scores:Vec<Score> = Vec::new();
        for j in 0..4{
            let mut rng = rand::thread_rng();
            let score;
            if rng.gen_range(0..10) >= 5{
                score = 0;
            } else {
                score = 10;
            }
            scores.push(Score { level: j, score: score });
        }
        let tmp = Item{
            name:Name().fake(),
            id:(2023000000..2023999999).fake::<i32>(),
            score:scores
        };
        result.push(tmp);
    }
    result
}
