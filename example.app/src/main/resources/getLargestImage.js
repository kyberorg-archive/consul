module.exports = async ({page, context}) => {
    const {url} = context;
    await page.goto(url);
    await page.evaluate(_ => {
        window.scrollBy(0, window.innerHeight);
    });

    const data = await page._client.send('Page.getResourceTree')
        .then(tree => {
            return Array.from(tree.frameTree.resources)
                .filter(resource => resource.type === 'Image' && resource.url && resource.url.indexOf('.svg') == -1)
                .sort((a, b) => b.contentSize - a.contentSize)[0];
        });

    return {
        data,
        type: 'json'
    };
};
