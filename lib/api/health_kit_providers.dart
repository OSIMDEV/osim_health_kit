enum ProviderType {
  huawei._("huawei"),
  samsung._("samsung");

  const ProviderType._(this._id);

  get id => _id;

  final String _id;
}
