export async function getLatestGames() {
  const LATEST_GAMES =
    "https://cors-anywhere.herokuapp.com/https://www.freetogame.com/api/games";

  const rawData = await fetch(LATEST_GAMES, {
    headers: {
      origin: "*",
      "x-requested-with": "HTTPClient",
    },
  });
  const games = await rawData.json();

  return games.map((item) => {
    const { short_description, id, release_date, thumbnail, title } = item;

    return {
      description: short_description,
      releaseDate: release_date,
      image: thumbnail,
      score: Math.floor(Math.random() * 100) + 1,
      slug: id,
      title,
    };
  });
}

export async function getGameDetails(slug) {
  const GAME_DETAILS = `https://internal-prod.apigee.fandom.net/v1/xapi/composer/metacritic/pages/games/${slug}/web?&apiKey=1MOZgmNFxvmljaQR1X9KAij9Mo4xAY3u`;

  const rawData = await fetch(GAME_DETAILS);
  const json = await rawData.json();

  const { components } = json;
  const { title, description, criticScoreSummary, images } = components[0];
  const { score } = criticScoreSummary;

  // get the card image
  const cardImage = images.find((image) => image.typeName === "cardImage");
  const { bucketType, bucketPath } = cardImage;
  const img = `https://www.metacritic.com/a/img/${bucketType}${bucketPath}`;

  const rawReviews = components[3].data.items;

  // get the reviews
  const reviews = rawReviews.map((review) => {
    const { quote, score, date, publicationName, author } = review;
    return { quote, score, date, publicationName, author };
  });

  return {
    img,
    title,
    slug,
    description,
    score,
    reviews,
  };
}
